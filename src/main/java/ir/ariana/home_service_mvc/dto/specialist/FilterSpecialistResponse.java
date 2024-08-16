package ir.ariana.home_service_mvc.dto.specialist;

public record FilterSpecialistResponse(Long id,
                                       String firstName,
                                       String lastName,
                                       String email,
                                       String password) {
}
