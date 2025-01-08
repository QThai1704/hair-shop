package t221124nqt.ecommerce.hair_shop.controller.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResCreateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResGetOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail.ResUpdateOrderDetailDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IOrderDetailService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailController {
    private final IOrderDetailService orderDetailService;

    public OrderDetailController(IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/order-details")
    @ApiMessage(message = "Tạo chi tiết đơn hàng")
    public ResponseEntity<ResCreateOrderDetailDTO> postCreateOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {
        OrderDetail newOrderDetail = this.orderDetailService.createOrderDetail(orderDetail);
        ResCreateOrderDetailDTO resCreateOrderDetailDTO = this.orderDetailService
                .convertResCreateOrderDetailDTO(newOrderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateOrderDetailDTO);
    }

    @GetMapping("/order-details/{id}")
    @ApiMessage(message = "Tìm kiếm chi tiết đơn hàng")
    public ResponseEntity<ResGetOrderDetailDTO> getMethodName(@PathVariable("id") long id) throws IdInvalidException {
        if (this.orderDetailService.checkExistById(id) == false) {
            throw new IdInvalidException("Chi tiết đơn hàng không tồn tại");
        }
        OrderDetail findOrderDetailById = this.orderDetailService.getOrderDetailById(id);
        ResGetOrderDetailDTO resGetOrderDetailDTO = this.orderDetailService
                .convertResGetOrderDetailDTO(findOrderDetailById);
        return ResponseEntity.ok().body(resGetOrderDetailDTO);
    }

    @GetMapping("/order-details")
    @ApiMessage(message = "Danh sách chi tiết đơn hàng")
    public ResponseEntity<ResPaginationDTO> getAllOrderDetail(@Filter Specification<OrderDetail> spec,
            Pageable pageable) {
        ResPaginationDTO resPaginationDTO = this.orderDetailService.getAllOrderDetails(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @PutMapping("/order-details")
    @ApiMessage(message = "Cập nhật chi tiết đơn hàng")
    public ResponseEntity<ResUpdateOrderDetailDTO> putUpdateOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {
        OrderDetail updateOrderDetail = this.orderDetailService.updateOrderDetail(orderDetail);
        ResUpdateOrderDetailDTO resUpdateOrderDetailDTO = this.orderDetailService
                .convertResUpdateOrderDetailDTO(updateOrderDetail);
        return ResponseEntity.ok().body(resUpdateOrderDetailDTO);
    }

    @DeleteMapping("/order-details/{id}")
    @ApiMessage(message = "Xóa chi tiết đơn hàng")
    public void deleteOrderDetail(@PathVariable("id") long id) throws IdInvalidException {
        if (this.orderDetailService.checkExistById(id) == false) {
            throw new IdInvalidException("Chi tiết đơn hàng không tồn tại");
        }
        this.orderDetailService.deleteOrderDetail(id);
    }
}
