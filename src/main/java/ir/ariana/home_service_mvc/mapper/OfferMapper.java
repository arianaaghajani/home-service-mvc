package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.offer.OfferReturn;
import ir.ariana.home_service_mvc.dto.offer.OfferSave;
import ir.ariana.home_service_mvc.dto.offer.OfferSaveRequest;
import ir.ariana.home_service_mvc.dto.offer.OfferSaveResponse;
import ir.ariana.home_service_mvc.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer offerSaveToModel(OfferSave offerSave);

    OfferReturn modelOfferToSaveResponse(Offer offer);

    List<OfferReturn> listOfferToSaveResponse(List<Offer> offers);
    Offer offerSaveRequestToModel(OfferSaveRequest request);

}
