package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.customer.CustomerReturn;
import ir.ariana.home_service_mvc.dto.customer.CustomerSaveRequest;
import ir.ariana.home_service_mvc.dto.customer.CustomerSaveResponse;
import ir.ariana.home_service_mvc.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerSaveRequestToModel(CustomerSaveRequest customerSaveRequest);

    CustomerReturn modelCustomerToSaveResponse(Customer customer);
    CustomerSaveResponse modelToCustomerSaveResponse(Customer customer);
    List<CustomerReturn> listCustomerToSaveResponse(List<Customer> customers);



}
