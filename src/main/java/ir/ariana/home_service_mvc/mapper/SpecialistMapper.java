package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.SpecialistReturn;
import ir.ariana.home_service_mvc.dto.SpecialistSaveRequest;
import ir.ariana.home_service_mvc.model.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SpecialistMapper {

    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    Specialist specialistSaveRequestToModel(SpecialistSaveRequest specialistSaveRequest);

    SpecialistReturn modelSpecialistToSaveResponse(Specialist specialist);

    List<SpecialistReturn> listSpecialistToSaveResponse(List<Specialist> specialists);

}
