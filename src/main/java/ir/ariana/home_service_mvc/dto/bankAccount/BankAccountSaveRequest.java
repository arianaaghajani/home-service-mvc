package ir.ariana.home_service_mvc.dto.bankAccount;


public record BankAccountSaveRequest(
        String cardNumber,
        String cvv2,
        Integer month,
        Integer year,
        String password,
        Long amount
) {
}
