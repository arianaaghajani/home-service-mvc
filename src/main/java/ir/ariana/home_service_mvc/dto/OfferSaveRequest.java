package ir.ariana.home_service_mvc.dto;


import ir.ariana.home_service_mvc.model.Order;
import ir.ariana.home_service_mvc.model.Specialist;

public record OfferSaveRequest(Specialist specialist,
                               Order order,
                               Long  proposedPrice) {
}
