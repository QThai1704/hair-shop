package t221124nqt.ecommerce.hair_shop.service.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResCreateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResGetOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResUpdateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.mapper.order.OrderDetailMapper;
import t221124nqt.ecommerce.hair_shop.repository.order.OrderDetailRepository;

@Service
public class IOrderDetailService implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public IOrderDetailService(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public ResCreateOrderDetailDTO convertResCreateOrderDetailDTO(OrderDetail orderDetail) {
        return this.orderDetailMapper.toResCreateOrderDetailDTO(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(long id) {
        Optional<OrderDetail> orderDetail = this.orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            return orderDetail.get();
        }
        return null;
    }

    @Override
    public ResGetOrderDetailDTO convertResGetOrderDetailDTO(OrderDetail orderDetail) {
        return this.orderDetailMapper.toResGetOrderDetailDTO(orderDetail);
    }

    @Override
    public ResPaginationDTO getAllOrderDetails(Specification<OrderDetail> spec, Pageable pageable) {
        Page<OrderDetail> orderDetailsPage = this.orderDetailRepository.findAll(spec, pageable);
        List<ResGetOrderDetailDTO> orderDetails = orderDetailsPage.getContent()
                .stream()
                .map(orderDetail -> this.orderDetailMapper.toResGetOrderDetailDTO(orderDetail))
                .collect(Collectors.toList());
        ResPaginationDTO res = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(orderDetailsPage, pageable);
        res.setMeta(meta);
        res.setData(orderDetails);
        return res;
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        OrderDetail currentOrderDetail = this.getOrderDetailById(orderDetail.getId());
        if (currentOrderDetail != null) {
            currentOrderDetail = this.orderDetailMapper.toOrderDetail(orderDetail);
            return this.orderDetailRepository.save(currentOrderDetail);
        }
        return null;
    }

    @Override
    public ResUpdateOrderDetailDTO convertResUpdateOrderDetailDTO(OrderDetail orderDetail) {
        return this.orderDetailMapper.toResUpdateOrderDetailDTO(orderDetail);
    }

    @Override
    public void deleteOrderDetail(long id) {
        this.orderDetailRepository.deleteById(id);
    }

    @Override
    public boolean checkExistById(long id) {
        return this.orderDetailRepository.existsById(id);
    }

}
