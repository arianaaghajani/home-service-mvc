package ir.ariana.home_service_mvc.dto;

import ir.ariana.home_service_mvc.enums.OrderStatus;
import ir.ariana.home_service_mvc.model.Address;

import java.util.Date;

public record OrderReturn(Long id,
                          CustomerReturn customer,
                          Address address,
                          SubServiceReturn subService,
                          OrderStatus orderStatus,
                          Long  proposedPrice,
                          String description,
                          Date needSpecialist) {
}
