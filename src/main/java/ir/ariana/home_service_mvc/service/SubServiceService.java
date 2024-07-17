package ir.ariana.home_service_mvc.service;

import ir.ariana.home_service_mvc.exception.DuplicateInformationException;
import ir.ariana.home_service_mvc.exception.NotFoundException;
import ir.ariana.home_service_mvc.model.MainService;
import ir.ariana.home_service_mvc.model.SubService;
import ir.ariana.home_service_mvc.repository.SubServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;


    @Transactional
    public SubService saveSubService(SubService subService) {
        if (subServiceRepository.findByName(subService.getName()).isPresent()) {
            log.error("duplicate subService name can not insert");
            throw new DuplicateInformationException("duplicate subService name can not insert");
        }
        subServiceRepository.save(subService);
        return subService;
    }

    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("subService with id " + id + " not founded"));
    }

    public SubService updateSubServicePrice(Long price, Long id) {
        SubService subService = findById(id);
        subService.setBasePrice(price);
        return subServiceRepository.save(subService);
    }

    public SubService updateSubServiceDescription(String description, Long id) {
        SubService subService = findById(id);
        subService.setDescription(description);
        return subServiceRepository.save(subService);
    }

    public List<SubService> findBySubService(MainService mainService) {
        List<SubService> subServices = subServiceRepository.findByMainService(mainService);
        if (subServices.isEmpty())
            throw new NullPointerException("no subService founded");
        return subServices;
    }

    public void removeSubService(Long id) {
        SubService subService = findById(id);
        subServiceRepository.delete(subService);
    }
}
