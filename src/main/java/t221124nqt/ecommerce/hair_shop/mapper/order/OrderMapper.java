package t221124nqt.ecommerce.hair_shop.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.order.Order;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResCreateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResGetOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResUpdateOrderDTO;

@Mapper(componentModel = "spring")
@SuppressWarnings("all")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    ResCreateOrderDTO toResCreateOrderDTO(Order order);

    ResUpdateOrderDTO toResUpdateOrderDTO(Order order);

    ResGetOrderDTO toResGetOrderDTO(Order order);

    Order toOrder(Order order);
}
