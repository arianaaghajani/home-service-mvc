package ir.ariana.home_service_mvc.dto.offer;


import ir.ariana.home_service_mvc.dto.order.OrderId;
import ir.ariana.home_service_mvc.dto.specialist.SpecialistId;

import java.time.LocalDateTime;

public record OfferSaveResponse(
        Long id,
        OrderId order,
        LocalDateTime creatOfferDate,
        Long suggestPrice,
        LocalDateTime updateOfferDate,
        SpecialistId specialistId,
        Boolean isAccepted
) {
}
