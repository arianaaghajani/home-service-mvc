package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.dto.OfferReturn;
import ir.ariana.home_service_mvc.dto.OfferSaveRequest;
import ir.ariana.home_service_mvc.dto.SpecialistReturn;
import ir.ariana.home_service_mvc.dto.SpecialistSaveRequest;
import ir.ariana.home_service_mvc.enums.OfferStatus;
import ir.ariana.home_service_mvc.enums.SpecialistStatus;
import ir.ariana.home_service_mvc.mapper.OfferMapper;
import ir.ariana.home_service_mvc.mapper.SpecialistMapper;
import ir.ariana.home_service_mvc.model.Offer;
import ir.ariana.home_service_mvc.model.Order;
import ir.ariana.home_service_mvc.model.Specialist;
import ir.ariana.home_service_mvc.model.SubService;
import ir.ariana.home_service_mvc.service.*;
import ir.ariana.home_service_mvc.validation.TakeAndCheckImage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SpecialistController {

    private final SpecialistService specialistService;
    private final TakeAndCheckImage takeAndCheckImage;
    private final SubServiceService subServiceService;
    private final OfferService offerService;
    private final OrderService orderService;
    private final CommentService commentService;


    @PostMapping("register_specialist")
    public ResponseEntity<SpecialistReturn> registerSpecialist(@Valid @RequestBody SpecialistSaveRequest specialistSaveRequest) {
        byte[] image = takeAndCheckImage.specialistImage(specialistSaveRequest.image());
        Specialist mappedSpecialist = SpecialistMapper.INSTANCE.specialistSaveRequestToModel(specialistSaveRequest);
        mappedSpecialist.setImage(image);
        Specialist savedSpecialist = specialistService.saveSpecialist(mappedSpecialist);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(savedSpecialist),
                HttpStatus.CREATED);
    }

    @GetMapping("specialist_SignIn")
    public ResponseEntity<SpecialistReturn> specialistSignIn(@RequestParam String email, String password) {
        Specialist specialist = specialistService.signInSpecialist(email, password);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(specialist),
                HttpStatus.FOUND);
    }

    @GetMapping("findBy_SpecialistId")
    public ResponseEntity<SpecialistReturn> findBySpecialistId(@RequestParam Long id) {
        Specialist specialist = specialistService.findById(id);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelSpecialistToSaveResponse(specialist),
                HttpStatus.FOUND);
    }

    @GetMapping("findBy_SpecialistStatus")
    public List<SpecialistReturn> findBySpecialistStatus(@RequestParam SpecialistStatus specialistStatus) {
        List<Specialist> specialists = specialistService.findBySpecialistStatus(specialistStatus);
        return SpecialistMapper.INSTANCE.listSpecialistToSaveResponse(specialists);
    }


    @GetMapping("save_Specialist_Image")
    public void saveSpecialistImage(@RequestParam Long id) {
        Specialist specialist = specialistService.findById(id);
        takeAndCheckImage.saveSpecialistImageToHDD(specialist.getImage(), specialist.getFirstName(),specialist.getLastName());
    }

    @GetMapping("update_Specialist_Password")
    public String updateSpecialistPassword(@RequestParam Long id, String oldPassword, String newPassword, String confirmPassword) {
        Specialist specialist = specialistService.findById(id);
        Specialist updatedSpecialist= specialistService.UpdatePassword(oldPassword, newPassword, confirmPassword, specialist);
        return updatedSpecialist.getPassword();
    }

    @DeleteMapping("delete_specialist")
    public String removeSpecialist(@RequestParam Long id) {
        specialistService.removeSpecialist(id);
        return "specialist and All relations have been deleted";
    }

    @PostMapping("save_Offer")
    public ResponseEntity<OfferReturn> saveOffer(@Valid @RequestBody OfferSaveRequest offerSaveRequest) {
        Offer mappedOffer = OfferMapper.INSTANCE.offerSaveRequestToModel(offerSaveRequest);
        Order order = orderService.findById(offerSaveRequest.order().getId());
        SubService subService = subServiceService.findById(order.getSubService().getId());
        Offer savedOffer = offerService.saveOffer(mappedOffer, subService, order);
        orderService.makeOrderStatusWaitForAccept(order);
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelOfferToSaveResponse(savedOffer),
                HttpStatus.CREATED);
    }


    @GetMapping("findOne_specialist_Offers")
    public List<OfferReturn> findOneSpecialistOffers(@RequestParam Long id) {
        Specialist specialist = specialistService.findById(id);
        List<Offer> offers = offerService.findSpecialistOffers(specialist);
        return OfferMapper.INSTANCE.listOfferToSaveResponse(offers);
    }

    @GetMapping("findOne_Order_Offers")
    public List<OfferReturn> findOneOrderOffers(@RequestParam Long id) {
        Order order = orderService.findById(id);
        List<Offer> offers = offerService.findOrderOffers(order);
        return OfferMapper.INSTANCE.listOfferToSaveResponse(offers);
    }


    @GetMapping("findBy_OfferStatus")
    public List<OfferReturn> findByOrderStatus(@RequestParam OfferStatus offerStatus) {
        List<Offer> offers = offerService.findByOfferStatus(offerStatus);
        return OfferMapper.INSTANCE.listOfferToSaveResponse(offers);
    }

    @GetMapping("show_Offer_Score")
    public String showOfferRate(@RequestParam Long id) {
        Offer offer = offerService.findById(id);
        return commentService.showDoneOfferScore(offer);
    }

}
