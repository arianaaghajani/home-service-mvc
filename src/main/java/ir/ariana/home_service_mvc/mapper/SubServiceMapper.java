package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.subService.SubServiceReturn;
import ir.ariana.home_service_mvc.dto.subService.SubServiceSaveRequest;
import ir.ariana.home_service_mvc.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = MainServiceMapper.class)
public interface SubServiceMapper {

    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService subServiceSaveRequestToModel(SubServiceSaveRequest SubServiceSaveRequest);

    SubServiceReturn modelSubServiceToSaveResponse(SubService subService);

    List<SubServiceReturn> listSubServiceToSaveResponse(List<SubService> subServices);
}
