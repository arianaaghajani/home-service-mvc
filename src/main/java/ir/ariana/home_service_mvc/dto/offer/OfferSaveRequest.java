package ir.ariana.home_service_mvc.dto.offer;


import ir.ariana.home_service_mvc.model.Order;
import ir.ariana.home_service_mvc.model.Specialist;
import jakarta.validation.constraints.NotNull;


public record OfferSaveRequest(
        Specialist specialist,
        Order order,
        @NotNull
        Long proposedPrice,
        Integer takeLong) {
}
