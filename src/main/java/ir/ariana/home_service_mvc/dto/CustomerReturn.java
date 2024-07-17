package ir.ariana.home_service_mvc.dto;

public record CustomerReturn(Integer id,
                             String firstName,
                             String lastName,
                             String email,
                             String password) {
}
