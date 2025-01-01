package t221124nqt.ecommerce.hair_shop.service;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResCreateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResGetOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.orderDetail.ResUpdateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;

public interface OrderDetailService {
    OrderDetail createOrderDetail(OrderDetail orderDetail);

    ResCreateOrderDetailDTO convertResCreateOrderDetailDTO(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(long id);

    ResGetOrderDetailDTO convertResGetOrderDetailDTO(OrderDetail orderDetail);

    ResPaginationDTO getAllOrderDetails(Specification<OrderDetail> spec, Pageable pageable);

    OrderDetail updateOrderDetail(OrderDetail orderDetail);

    ResUpdateOrderDetailDTO convertResUpdateOrderDetailDTO(OrderDetail orderDetail);

    void deleteOrderDetail(long id);

    boolean checkExistById(long id);
}
