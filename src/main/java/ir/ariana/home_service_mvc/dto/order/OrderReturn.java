package ir.ariana.home_service_mvc.dto.order;

import ir.ariana.home_service_mvc.dto.customer.CustomerReturn;
import ir.ariana.home_service_mvc.dto.subService.SubServiceReturn;
import ir.ariana.home_service_mvc.enums.OrderStatus;
import ir.ariana.home_service_mvc.model.Address;


public record OrderReturn(Long id,
                          CustomerReturn customer,
                          Address address,
                          SubServiceReturn subService,
                          Long proposedPrice,
                          String description,
                          OrderStatus status,
                          Long specialistId
) {
}
