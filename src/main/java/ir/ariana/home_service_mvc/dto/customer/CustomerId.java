package ir.ariana.home_service_mvc.dto.customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerId(
        @NotNull
        @Min(0L)
        Long id
) {
}
