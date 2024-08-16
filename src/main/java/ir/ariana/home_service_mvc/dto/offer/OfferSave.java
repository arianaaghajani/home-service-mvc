package ir.ariana.home_service_mvc.dto.offer;

public record OfferSave(Long specialistId,
                        Long proposedPrice,
                        Long orderId,
                        Integer takeLong,
                        Long subServiceId) {
}
