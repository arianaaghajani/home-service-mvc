package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.AddressDTO;
import ir.ariana.home_service_mvc.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    public Address convertToAddress(AddressDTO dto) {
        return new Address(
                dto.getProvince(),
                dto.getCity(),
                dto.getAvenue(),
                dto.getPostalCode(),
                dto.getHouseNumber(),
                dto.getMoreDescription()
        );
    }

    public AddressDTO convertToDTO(Address address) {
        return new AddressDTO(
                address.getProvince(),
                address.getCity(),
                address.getAvenue(),
                address.getPostalCard(),
                address.getHouseNumber(),
                address.getMoreDescription()
        );
    }

}
