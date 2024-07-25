package ir.ariana.home_service_mvc.dto;


import ir.ariana.home_service_mvc.enums.OrderStatus;
import ir.ariana.home_service_mvc.model.Address;
import ir.ariana.home_service_mvc.model.Customer;
import ir.ariana.home_service_mvc.model.SubService;


public record OrderSaveRequest(Address address,
                               Customer customer,
                               OrderStatus orderStatus,
                               SubService subService,
                               Long proposedPrice,
                               String description,
                               String needSpecialistDate) {
}
