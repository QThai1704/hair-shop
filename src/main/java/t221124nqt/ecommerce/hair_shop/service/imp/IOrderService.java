package t221124nqt.ecommerce.hair_shop.service.imp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.order.Order;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResCreateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResGetOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResUpdateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.mapper.order.OrderMapper;
import t221124nqt.ecommerce.hair_shop.repository.OrderRepository;
import t221124nqt.ecommerce.hair_shop.service.OrderService;

@Service
public class IOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public IOrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public ResCreateOrderDTO convertResCreateOrderDTO(Order order) {
        return this.orderMapper.toResCreateOrderDTO(order);
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        return null;
    }

    @Override
    public ResGetOrderDTO convertResGetOrderDTO(Order order) {
        return this.orderMapper.toResGetOrderDTO(order);
    }

    @Override
    public ResPaginationDTO getAllOrder(Specification<Order> spec, Pageable pageable) {
        Page<Order> ordersPage = this.orderRepository.findAll(spec, pageable);
        List<ResGetOrderDTO> orders = ordersPage.getContent()
                .stream()
                .map(order -> this.orderMapper.toResGetOrderDTO(order))
                .collect(Collectors.toList());
        ResPaginationDTO res = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(ordersPage, pageable);
        res.setMeta(meta);
        res.setData(orders);
        return res;
    }

    @Override
    public Order updateOrder(Order order) {
        Order currentOrder = this.getOrderById(order.getId());
        if (currentOrder != null) {
            Timestamp createAt = currentOrder.getCreatedAt();
            currentOrder = this.orderMapper.toOrder(order);
            currentOrder.setCreatedAt(createAt);
            return this.orderRepository.save(order);
        }
        return null;
    }

    @Override
    public ResUpdateOrderDTO convertResUpdateOrderDTO(Order order) {
        return this.orderMapper.toResUpdateOrderDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public boolean checkExistById(Long id) {
        return this.orderRepository.existsById(id);
    }
}
