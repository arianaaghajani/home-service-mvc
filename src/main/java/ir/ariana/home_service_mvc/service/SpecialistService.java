package ir.ariana.home_service_mvc.service;

import ir.ariana.home_service_mvc.dto.specialist.SpecialistCriteriaDTO;
import ir.ariana.home_service_mvc.enums.SpecialistStatus;
import ir.ariana.home_service_mvc.exception.*;
import ir.ariana.home_service_mvc.model.Specialist;
import ir.ariana.home_service_mvc.repository.SpecialistRepository;
import ir.ariana.home_service_mvc.specification.SpecialistSpecifications;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    private final EntityManager entityManager;


    public boolean validate(Specialist specialist) {
        Set<ConstraintViolation<Specialist>> violations = validator.validate(specialist);
        if (violations.isEmpty()) {
            Specialist save = specialistRepository.save(specialist);
            specialist.setId(save.getId());
            log.info("specialist saved");
        } else {
            System.out.println("Invalid user data found:");
            for (ConstraintViolation<Specialist> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new InvalidInputInformationException("some of inputs are not valid");
        }
        return false;
    }

    public Specialist updateSpecialist(Specialist specialist) {
        findById(specialist.getId());
//        if (!validate(specialist)) {
//            throw new InvalidEntityException(String.format("the expert with %s have invalid variable",
//                    specialist.getPassword()));
//        }
        return specialistRepository.save(specialist);
    }


    @Transactional
    public Specialist saveSpecialist(Specialist specialist) {
        if (specialistRepository.findByEmail(specialist.getEmail()).isPresent()) {
            log.error("duplicate email can not insert");
            throw new DuplicateInformationException("duplicate email can not insert");
        } else
//            validate(specialist);
//        return specialist;
            return specialistRepository.save(specialist);
    }

    @Transactional
    public Specialist signInSpecialist(String email, String password) {
        return specialistRepository.findByEmailAndPassword(email, password).orElseThrow(() ->
                new NotFoundException("wrong email or password"));
    }

    public Specialist findById(Long id) {
        return specialistRepository.findById(id).orElseThrow(() ->
                new NotFoundException("specialist with id " + id + " not founded"));
    }


    public Specialist UpdatePassword(String oldPassword, String newPassword, String confirmPassword,
                                     Specialist specialist) {
        if (!specialist.getPassword().equals(oldPassword))
            throw new NotMatchPasswordException("wrong password entered");
        if (!newPassword.equals(confirmPassword))
            throw new NotMatchPasswordException("different password entered");
        specialist.setPassword(newPassword);
        return specialistRepository.save(specialist);
    }

    public Specialist updateSpecialistStatus(SpecialistStatus specialistStatus, Specialist specialist) {
        specialist.setSpecialistStatus(specialistStatus);
        validate(specialist);
        return specialist;
    }

    @Transactional
    public List<Specialist> findBySpecialistStatus(SpecialistStatus specialistStatus) {
        List<Specialist> specialists = specialistRepository.findBySpecialistStatus(specialistStatus);
        if (!specialists.isEmpty())
            return specialists;
        else
            throw new NullPointerException();
    }

    public boolean access(Specialist specialist) {
        SpecialistStatus specialistStatus = specialist.getSpecialistStatus();
        return specialistStatus == SpecialistStatus.CONFIRMED;
    }

    public void removeSpecialist(Long id) {
        Specialist specialist = findById(id);
        specialistRepository.delete(specialist);
    }

    public void setScore(Integer score, Specialist specialist) {
        specialist.setScore(score);
        specialistRepository.save(specialist);
    }

    public Specialist save(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    public void blockSpecialist(Specialist specialist) {
        double score = specialist.getScore();
        if (score < 0)
            updateSpecialistStatus(SpecialistStatus.BLOCKED, specialist);

    }

    public List<Specialist> specialistSearch(SpecialistCriteriaDTO specialistCriteriaDTO) {
        Specification<Specialist> specification = SpecialistSpecifications.getSpecialistSpecification(specialistCriteriaDTO);
        return specialistRepository.findAll(specification);
    }

}
