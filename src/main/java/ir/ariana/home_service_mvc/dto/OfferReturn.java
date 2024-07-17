package ir.ariana.home_service_mvc.dto;

import ir.ariana.home_service_mvc.enums.OfferStatus;

public record OfferReturn(Long id,
                          SpecialistReturn specialist,
                          OrderReturn order,
                          OfferStatus offerStatus,
                          Long  proposedPrice) {
}
