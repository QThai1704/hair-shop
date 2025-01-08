package t221124nqt.ecommerce.hair_shop.service.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.order.Order;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResCreateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResGetOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResUpdateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;

public interface OrderService {
    Order createOrder(Order order);

    ResCreateOrderDTO convertResCreateOrderDTO(Order order);

    Order getOrderById(Long id);

    ResGetOrderDTO convertResGetOrderDTO(Order order);

    ResPaginationDTO getAllOrder(Specification<Order> spec, Pageable pageable);

    Order updateOrder(Order order);

    ResUpdateOrderDTO convertResUpdateOrderDTO(Order order);

    void deleteOrder(Long id);

    boolean checkExistById(Long id);
}
