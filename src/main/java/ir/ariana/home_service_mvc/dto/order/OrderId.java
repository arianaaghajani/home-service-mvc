package ir.ariana.home_service_mvc.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderId(
        @NotNull
        @Min(0L)
        Long id
) {
}
