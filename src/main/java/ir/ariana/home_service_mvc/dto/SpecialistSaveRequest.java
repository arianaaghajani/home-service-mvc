package ir.ariana.home_service_mvc.dto;

public record SpecialistSaveRequest(String firstName,
                                    String lastName,
                                    String email,
                                    String password,
                                    String image) {
}
