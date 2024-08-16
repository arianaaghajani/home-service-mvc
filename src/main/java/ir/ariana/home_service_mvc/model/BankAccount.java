package ir.ariana.home_service_mvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Getter
@Setter
@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    @Pattern(regexp = "[0-9]+")
    @Size(max = 16)
    private String cardNumber;
    @NotBlank
    @Pattern(regexp = "[0-9]+")
    @Size(max = 5, min = 3)
    private String cvv2;
    @NotNull
//    @Range(min = 1, max = 4)
    private Integer month;
    @NotNull
    @Range(min = 1400, max = 1410)
    private Integer year;
    @NotBlank
    @Pattern(regexp = "[0-9]+")
    @Size(max = 8, min = 5)
    private String password;
    @Min(value = 0,message = "Your bank account balance is insufficient ")
    Long amount;

}

