package ir.ariana.home_service_mvc.dto.offer;

import ir.ariana.home_service_mvc.dto.order.OrderReturn;
import ir.ariana.home_service_mvc.dto.specialist.SpecialistReturn;
import ir.ariana.home_service_mvc.enums.OfferStatus;

public record OfferReturn(Long id,
                          OrderReturn order,
                          SpecialistReturn specialist,
                          OfferStatus offerStatus,
                          Long proposedPrice,
                          Long subServiceId) {
}
