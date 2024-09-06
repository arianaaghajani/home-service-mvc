package ir.ariana.home_service_mvc.specification;

import ir.ariana.home_service_mvc.dto.specialist.SpecialistCriteriaDTO;
import ir.ariana.home_service_mvc.model.Specialist;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecialistSpecifications {

    public static Specification<Specialist> getSpecialistSpecification(SpecialistCriteriaDTO specialistCriteriaDto) {
        return (root, query, criteriaBuilder) ->{
            List<Predicate> predicates =new ArrayList<>();

            if (specialistCriteriaDto.firstName!=null&& !specialistCriteriaDto.firstName.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("firstName"),specialistCriteriaDto.firstName));
            if (specialistCriteriaDto.lastName!=null&& !specialistCriteriaDto.lastName.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("lastName"),specialistCriteriaDto.lastName));
            if (specialistCriteriaDto.email!=null&& !specialistCriteriaDto.email.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("email"),specialistCriteriaDto.email));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
