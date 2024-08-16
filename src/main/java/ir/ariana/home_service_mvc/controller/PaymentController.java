package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.enums.OfferStatus;
import ir.ariana.home_service_mvc.enums.OrderStatus;
import ir.ariana.home_service_mvc.model.*;
import ir.ariana.home_service_mvc.recaptch.RecaptchaService;
import ir.ariana.home_service_mvc.service.OfferService;
import ir.ariana.home_service_mvc.service.OrderService;
import ir.ariana.home_service_mvc.service.SpecialistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.ModelAndViewContainer;


@Controller
@ResponseBody
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final OrderService orderService;
    private final OfferService offerService;
    private final RecaptchaService validator;
    private final SpecialistService specialistService;

    @ModelAttribute
    public BankAccount setForm() {
        return new BankAccount();
    }

    @GetMapping("/pay")
    public ModelAndViewContainer getPayment() {
        BankAccount bankAccount = new BankAccount();
        ModelAndViewContainer mav = new ModelAndViewContainer();
        mav.addAttribute("finalPayment", bankAccount);
        Order order = orderService.findById(1L);
        Offer offer = offerService.findByOrderAndOfferStatus(order, OfferStatus.DONE).get(0);
        Long proposedPrice = offer.getProposedPrice();
        String message = "you have to pay " + proposedPrice + " for your order";
        mav.addAttribute("price", message);
        return mav;
    }

    @PostMapping("/pay")
    public ModelAndViewContainer doPayment(@ModelAttribute("finalPayment") @Valid BankAccount bankAccount, BindingResult result,
                                           @RequestParam(name = "g-recaptcha-response")
                                           String captcha) {
        ModelAndViewContainer mav = new ModelAndViewContainer();

        if (!validator.validateCaptcha(captcha)) {
            mav.addAttribute("message", "Please Verify Captcha");
        }

        if (result.hasErrors()) {
            new ModelAndViewContainer();
        }
        Order order = orderService.findById(3L);
        Offer offer = offerService.findByOrderAndOfferStatus(order, OfferStatus.DONE).get(0);
        Specialist specialist = offer.getSpecialist();
        orderService.updateOrderStatus(OrderStatus.PAID, order);
        double credit = specialist.getCredit();
        specialist.setCredit(((double) (offer.getProposedPrice() * 70) / 100) + credit);
        specialistService.save(specialist);
        mav.addAttribute("finalPayment", result.getTarget());
        return mav;
    }


}