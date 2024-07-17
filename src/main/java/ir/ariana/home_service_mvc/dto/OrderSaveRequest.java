package ir.ariana.home_service_mvc.dto;


import ir.ariana.home_service_mvc.model.Customer;
import ir.ariana.home_service_mvc.model.SubService;

public record OrderSaveRequest(Customer customer,
                               SubService subService,
                               Long proposedPrice,
                               String description,
                               String needSpecialistDate) {
}
