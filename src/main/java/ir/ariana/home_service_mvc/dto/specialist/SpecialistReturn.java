package ir.ariana.home_service_mvc.dto.specialist;

public record SpecialistReturn(Long id,
                               String firstName,
                               String lastName,
                               String email,
                               String password,
                               double score,
                               byte[] image) {
}
