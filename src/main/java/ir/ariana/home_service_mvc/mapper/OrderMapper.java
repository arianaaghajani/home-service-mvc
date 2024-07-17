package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.OrderReturn;
import ir.ariana.home_service_mvc.dto.OrderSaveRequest;
import ir.ariana.home_service_mvc.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = {CustomerMapper.class, SubServiceMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    Order orderSaveRequestToModel(OrderSaveRequest request);

    OrderReturn modelOrderToSaveResponse(Order order);

    List<OrderReturn> listOrderToSaveResponse(List<Order> orders);
}
