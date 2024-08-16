package ir.ariana.home_service_mvc.mapper;


import ir.ariana.home_service_mvc.dto.mainService.MainServiceReturn;
import ir.ariana.home_service_mvc.dto.mainService.MainServiceSaveRequest;
import ir.ariana.home_service_mvc.model.MainService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface MainServiceMapper {

    MainServiceMapper INSTANCE = Mappers.getMapper(MainServiceMapper.class);

    MainService mainServiceSaveRequestToModel(MainServiceSaveRequest mainServiceSaveRequest);

    MainServiceReturn modelMainServiceToSaveResponse(MainService mainService);

    List<MainServiceReturn> listMainServiceToSaveResponse(List<MainService> mainServices);
}
