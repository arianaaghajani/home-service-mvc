package ir.ariana.home_service_mvc.dto.specialist;

public record SpecialistSaveRequest(String firstName,
                                    String lastName,
                                    String email,
                                    String password,
                                    String imagePath) {
}
