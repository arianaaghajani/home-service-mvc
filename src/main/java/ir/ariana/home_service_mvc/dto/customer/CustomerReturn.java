package ir.ariana.home_service_mvc.dto.customer;

public record CustomerReturn(Long id,
                             String firstName,
                             String lastName,
                             String email,
                             String password) {
}
