package ir.ariana.home_service_mvc.dto.payment;

import jakarta.validation.constraints.Pattern;

public record PaymentSaveRequest(
        @Pattern(regexp ="^(6037|6104|6273|6219)[0-9]{12}$",message = "bank card not valid")
        String cardNumber,
        @Pattern(regexp ="^[0-9]{3,4}$",message = "cvv not valid")
        String cvv2,
        @Pattern(regexp ="^(0[1-9]|1[0-2])$",message = "month must between '01' and '12'")
        String month,
        @Pattern(regexp ="^(0[1-9]|[1-9][0-9])$",message = "year must between '01' and '99'")
        String year,
        @Pattern(regexp ="^[1-9][0-9]{5}$",message = "password must 6 digit first digit not be zero")
        String password,
        String captchaAnswer
) {
}
