package ir.ariana.home_service_mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String province;
    @NonNull
    String city;
    @NonNull
    String avenue;
    @NonNull
    @Column(unique = true)
    String postalCard;
    @Column(unique = true)
    String houseNumber;
    String moreDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    Customer customer;

}
