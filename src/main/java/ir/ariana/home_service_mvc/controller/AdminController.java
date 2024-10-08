package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.dto.customer.CustomerCriteriaDto;
import ir.ariana.home_service_mvc.dto.customer.CustomerSaveResponse;
import ir.ariana.home_service_mvc.dto.mainService.MainServiceReturn;
import ir.ariana.home_service_mvc.dto.mainService.MainServiceSaveRequest;
import ir.ariana.home_service_mvc.dto.specialist.SpecialistCriteriaDTO;
import ir.ariana.home_service_mvc.dto.specialist.SpecialistReturn;
import ir.ariana.home_service_mvc.dto.subService.SubServiceReturn;
import ir.ariana.home_service_mvc.dto.subService.SubServiceSaveRequest;
import ir.ariana.home_service_mvc.enums.SpecialistStatus;
import ir.ariana.home_service_mvc.mapper.*;
import ir.ariana.home_service_mvc.model.*;
import ir.ariana.home_service_mvc.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
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

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("enter_subService")
    public ResponseEntity<SubServiceReturn> enterSubService(@Validated @RequestBody SubServiceSaveRequest newSubService) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceSaveRequestToModel(newSubService);
        SubService savedSubService = subServiceService.saveSubService(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(savedSubService),
                HttpStatus.CREATED);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("findBy_SubServiceId")
    public ResponseEntity<SubServiceReturn> findBySubServiceId(@RequestParam Long id) {
        SubService subService = subServiceService.findById(id);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(subService),
                HttpStatus.FOUND);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("update_SubService_Price")
    public ResponseEntity<SubServiceReturn> updateSubServicePrice(@RequestParam Long id, Long newPrice) {
        SubService subService = subServiceService.updateSubServicePrice(newPrice, id);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelSubServiceToSaveResponse(subService),
                HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
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

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("make_Specialist_Accepted")
    public ResponseEntity<SpecialistReturn> makeSpecialistAccepted(@RequestParam Long id, SpecialistStatus specialistStatus) {
        Specialist specialist = specialistService.findById(id);
        Specialist updated = specialistService.updateSpecialistStatus(specialistStatus, specialist);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(updated),
                HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("add_Specialist_ToSubService")
    public ResponseEntity<SpecialistReturn> addSpecialistToSubService(@RequestParam Long specialistId, Long subServiceId) {
        Specialist specialist = specialistService.findById(specialistId);
        Specialist addedSpecialist = adminService.addSpecialistToSubService(specialist, subServiceId);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(addedSpecialist),
                HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("remove_Specialist_SubService")
    public ResponseEntity<SpecialistReturn> removeSpecialistSubService(@RequestParam Long id, Long subServiceId) {
        Specialist specialist = specialistService.findById(id);
        Specialist specialist1 = adminService.removeSpecialistFromSubService(specialist, subServiceId);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(specialist1),
                HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete_mainService")
    public String removeMainService(@RequestParam Long id) {
        mainServiceService.removeMainService(id);
        return "mainService and All relations have been deleted";
    }

    @PatchMapping("block_Specialist")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SpecialistReturn> blockExpert(@RequestParam Long id) {
        Specialist specialist = specialistService.findById(id);
        specialist.setScore(-1);
        specialistService.save(specialist);
        specialistService.blockSpecialist(specialist);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(specialist),
                HttpStatus.FOUND);
    }


    @GetMapping("search-customer")
    public List<CustomerSaveResponse> searchCustomer(@RequestBody CustomerCriteriaDto customerCriteriaDto){
        List<Customer> customers =customerService.customerSearch(customerCriteriaDto);
        return customers.stream()
                .map(CustomerMapper.INSTANCE::modelToCustomerSaveResponse).collect(Collectors.toList());
    }


    @GetMapping("search-specialist")
    public List<SpecialistReturn> searchSpecialist(@RequestBody SpecialistCriteriaDTO specialistCriteriaDto){
        List<Specialist> specialists =specialistService.specialistSearch(specialistCriteriaDto);
        return specialists.stream()
                .map(SpecialistMapper.INSTANCE::modelSpecialistToSaveResponse).collect(Collectors.toList());
    }
}
