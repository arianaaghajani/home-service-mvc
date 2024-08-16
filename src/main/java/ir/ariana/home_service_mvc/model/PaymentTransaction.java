package ir.ariana.home_service_mvc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "PaymentTransaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderId;
    Long proposedPrice;
    String cardNumber;
    String cvv2;
    String month;
    String year;
    String password;
    String captchaAnswer;
    LocalDateTime time;
}
