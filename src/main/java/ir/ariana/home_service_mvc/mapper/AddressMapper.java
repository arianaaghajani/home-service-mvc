package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.address.AddressDTO;
import ir.ariana.home_service_mvc.model.Address;
import org.mapstruct.factory.Mappers;

public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address addressSaveRequestToModel(AddressDTO addressDTO);
    AddressDTO modelAddressToSaveResponse(Address address);


}
