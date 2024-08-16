package ir.ariana.home_service_mvc.dto.specialist;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SpecialistId(@NotNull
                           @Min(0L)
                           Long specialistId) {
}
