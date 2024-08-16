package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.bankAccount.BankAccountSaveRequest;
import ir.ariana.home_service_mvc.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount bankAccountSaveRequestToModel(BankAccountSaveRequest request);
}
