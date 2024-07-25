package ir.ariana.home_service_mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

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
    String postalCard;
    String houseNumber;
    String moreDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    Customer customer;


    public Address(@NonNull String province,@NonNull String city, @NonNull String avenue,
                   @NonNull String postalCard,String houseNumber, String moreDescription) {
        this.province = province;
        this.city = city;
        this.avenue = avenue;
        this.postalCard = postalCard;
        this.houseNumber = houseNumber;
        this.moreDescription = moreDescription;
    }
}
