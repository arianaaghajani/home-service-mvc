package ir.ariana.home_service_mvc.dto;

public record CustomerSaveRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password) {
}
