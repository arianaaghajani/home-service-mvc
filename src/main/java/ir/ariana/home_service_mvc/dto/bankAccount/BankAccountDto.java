package ir.ariana.home_service_mvc.dto.bankAccount;

public record BankAccountDto(String cardNumber,
                               String cvv2,
                               Integer month,
                               Integer year,
                               String password) {
}
