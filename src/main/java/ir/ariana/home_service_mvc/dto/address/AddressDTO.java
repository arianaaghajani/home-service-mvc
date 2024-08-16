package ir.ariana.home_service_mvc.dto.address;

public record AddressDTO(String city,
                         String avenue,
                         String province,
                         String postalCard,
                         String houseNumber,
                         String moreDescription) {
}
