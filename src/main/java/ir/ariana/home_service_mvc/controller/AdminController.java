package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.dto.MainServiceReturn;
import ir.ariana.home_service_mvc.dto.MainServiceSaveRequest;
import ir.ariana.home_service_mvc.dto.SubServiceReturn;
import ir.ariana.home_service_mvc.dto.SubServiceSaveRequest;
import ir.ariana.home_service_mvc.mapper.MainServiceMapper;
import ir.ariana.home_service_mvc.mapper.SubServiceMapper;
import ir.ariana.home_service_mvc.model.MainService;
import ir.ariana.home_service_mvc.model.SubService;
import ir.ariana.home_service_mvc.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MainServiceService mainServiceService;
    private final SubServiceService subServiceService;
    private final SpecialistService specialistService;
    private final CustomerService customerService;


    @PostMapping("enter_mainService")
    public ResponseEntity<MainServiceReturn> enterMainService(@Validated @RequestBody MainServiceSaveRequest newMainService) {
        MainService mappedMainService = MainServiceMapper.INSTANCE.mainServiceSaveRequestToModel(newMainService);
        MainService savedMainService = mainServiceService.saveMainService(mappedMainService);
        return new ResponseEntity<>(MainServiceMapper.INSTANCE.modelMainServiceToSaveResponse(savedMainService),
                HttpStatus.CREATED);
    }

    @GetMapping("show_All_mainServices")
    public List<MainServiceReturn> showAllMainServices() {
        List<MainService> mainServices = mainServiceService.showAllMainService();
        return MainServiceMapper.INSTANCE.listMainServiceToSaveResponse(mainServices);
    }

    @GetMapping("findBy_mainServiceId")
    public ResponseEntity<MainServiceReturn> findByMainServiceId(@RequestParam Long id) {
        MainService mainService = mainServiceService.findById(id);
        return new ResponseEntity<>(MainServiceMapper.INSTANCE.modelMainServiceToSaveResponse(mainService),
                HttpStatus.FOUND);
    }

    @PostMapping("enter_subService")
    public ResponseEntity<SubServiceReturn> enterSubService(@Validated @RequestBody SubServiceSaveRequest newSubService) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceSaveRequestToModel(newSubService);
        SubService savedSubService = subServiceService.saveSubService(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(savedSubService),
                HttpStatus.CREATED);
    }

    @GetMapping("findBy_SubServiceId")
    public ResponseEntity<SubServiceReturn> findBySubServiceId(@RequestParam Long id) {
        SubService subService = subServiceService.findById(id);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(subService),
                HttpStatus.FOUND);
    }

    @GetMapping("update_SubService_Price")
    public ResponseEntity<SubServiceReturn> updateSubServicePrice(@RequestParam Long id, Long newPrice) {
        SubService subService = subServiceService.updateSubServicePrice(newPrice, id);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(subService),
                HttpStatus.OK);
    }

    @GetMapping("update_SubService_Description")
    public ResponseEntity<SubServiceReturn> updateSubServiceDescription(@RequestParam Long id, String newDescription) {
        SubService subService = subServiceService.updateSubServiceDescription(newDescription, id);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(subService),
                HttpStatus.OK);
    }

}
