package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.dto.*;
import ir.ariana.home_service_mvc.enums.SpecialistStatus;
import ir.ariana.home_service_mvc.mapper.MainServiceMapper;
import ir.ariana.home_service_mvc.mapper.SpecialistMapper;
import ir.ariana.home_service_mvc.mapper.SubServiceMapper;
import ir.ariana.home_service_mvc.model.MainService;
import ir.ariana.home_service_mvc.model.Specialist;
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

    @GetMapping("show_SubService_For_One_MainService")
    public List<SubServiceReturn> showSubServiceForOneMainService(@RequestParam Long id) {
        MainService mainService = mainServiceService.findById(id);
        List<SubService> subServices = subServiceService.findBySubService(mainService);
        return SubServiceMapper.INSTANCE.listSubServiceToSaveResponse(subServices);
    }

    @GetMapping("make_Specialist_Accepted")
    public ResponseEntity<SpecialistReturn> makeSpecialistAccepted(@RequestParam Long id, SpecialistStatus specialistStatus) {
        Specialist specialist = specialistService.findById(id);
        Specialist updated = specialistService.updateSpecialistStatus(specialistStatus, specialist);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(updated),
                HttpStatus.OK);
    }

    @GetMapping("add_Specialist_ToSubService")
    public ResponseEntity<SpecialistReturn> addSpecialistToSubService(@RequestParam Long specialistId, Long subServiceId) {
        Specialist specialist = specialistService.findById(specialistId);
        Specialist addedSpecialist = adminService.addSpecialistToSubService(specialist, subServiceId);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(addedSpecialist),
                HttpStatus.OK);
    }

    @GetMapping("remove_Specialist_SubService")
    public ResponseEntity<SpecialistReturn> removeSpecialistSubService(@RequestParam Long id, Long subServiceId) {
        Specialist specialist = specialistService.findById(id);
        Specialist specialist1 = adminService.removeSpecialistFromSubService(specialist, subServiceId);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(specialist1),
                HttpStatus.OK);
    }

    @DeleteMapping("delete_mainService")
    public String removeMainService(@RequestParam Long id) {
        mainServiceService.removeMainService(id);
        return "mainService and All relations have been deleted";
    }
}
