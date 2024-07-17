package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.OfferReturn;
import ir.ariana.home_service_mvc.dto.OfferSaveRequest;
import ir.ariana.home_service_mvc.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SpecialistMapper.class, OrderMapper.class})
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer offerSaveRequestToModel(OfferSaveRequest request);

    OfferReturn modelOfferToSaveResponse(Offer offer);

    List<OfferReturn> listOfferToSaveResponse(List<Offer> offers);
}
