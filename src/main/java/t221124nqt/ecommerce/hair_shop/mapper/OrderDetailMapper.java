package t221124nqt.ecommerce.hair_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResCreateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResGetOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResUpdateOrderDetailDTO;

@Mapper(componentModel = "spring")
@SuppressWarnings("all")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    ResCreateOrderDetailDTO toResCreateOrderDetailDTO(OrderDetail orderDetail);

    ResUpdateOrderDetailDTO toResUpdateOrderDetailDTO(OrderDetail orderDetail);

    ResGetOrderDetailDTO toResGetOrderDetailDTO(OrderDetail orderDetail);

    OrderDetail toOrderDetail(OrderDetail orderDetail);
}
