package ir.ariana.home_service_mvc.dto.payment;

import java.time.LocalDateTime;

public record PaymentSaveResponse(
        String cardNumber,
        LocalDateTime time
) {
}
