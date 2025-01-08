package t221124nqt.ecommerce.hair_shop.controller.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.domain.order.Order;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResCreateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResGetOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.ResUpdateOrderDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IOrderService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    @ApiMessage(message = "Tạo đơn hàng")
    public ResponseEntity<ResCreateOrderDTO> postCreateOrder(@Valid @RequestBody Order order) {
        Order newOrder = this.orderService.createOrder(order);
        ResCreateOrderDTO resCreateOrderDTO = this.orderService.convertResCreateOrderDTO(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateOrderDTO);
    }

    @GetMapping("/orders/{id}")
    @ApiMessage(message = "Tìm kiếm đơn hàng")
    public ResponseEntity<ResGetOrderDTO> getMethodName(@PathVariable("id") long id) throws IdInvalidException {
        if (this.orderService.checkExistById(id) == false) {
            throw new IdInvalidException("Đơn hàng không tồn tại");
        }
        Order findOrderById = this.orderService.getOrderById(id);
        ResGetOrderDTO resGetOrderDTO = this.orderService.convertResGetOrderDTO(findOrderById);
        return ResponseEntity.ok().body(resGetOrderDTO);
    }

    @GetMapping("/orders")
    @ApiMessage(message = "Danh sách đơn hàng")
    public ResponseEntity<ResPaginationDTO> getAllOrder(@Filter Specification<Order> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = this.orderService.getAllOrder(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @PutMapping("/orders")
    @ApiMessage(message = "Cập nhật đơn hàng")
    public ResponseEntity<ResUpdateOrderDTO> updateOrder(@Valid @RequestBody Order order) {
        Order updateOrder = this.orderService.updateOrder(order);
        ResUpdateOrderDTO resUpdateOrderDTO = this.orderService.convertResUpdateOrderDTO(updateOrder);
        return ResponseEntity.ok().body(resUpdateOrderDTO);
    }

    @DeleteMapping("/orders/{id}")
    @ApiMessage(message = "Xóa đơn hàng")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") long id) throws IdInvalidException {
        if (this.orderService.checkExistById(id) == false) {
            throw new IdInvalidException("Id không tồn tại");
        }
        this.orderService.deleteOrder(id);
        return ResponseEntity.ok().body(null);
    }
}
