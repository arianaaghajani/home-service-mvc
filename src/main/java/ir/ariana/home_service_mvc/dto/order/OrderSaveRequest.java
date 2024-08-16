package ir.ariana.home_service_mvc.dto.order;


import ir.ariana.home_service_mvc.dto.address.AddressDTO;

public record OrderSaveRequest(AddressDTO address,
                               Long customerId,
                               Long subServiceId,
                               Long proposedPrice,
                               String description,
                               Long specialistId) {
}
