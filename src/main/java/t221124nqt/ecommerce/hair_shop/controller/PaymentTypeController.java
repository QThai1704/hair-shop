package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResUpdatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IPaymentTypeService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1")
public class PaymentTypeController {
    private final IPaymentTypeService paymentTypeService;

    public PaymentTypeController(IPaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @PostMapping("/paymentTypes")
    @ApiMessage(message = "Tạo mới loại thanh toán")
    public ResponseEntity<ResCreatePaymentDTO> createPaymentTypes(@RequestBody PaymentType paymentType) {
        PaymentType newPaymentType = paymentTypeService.createPaymentType(paymentType);
        ResCreatePaymentDTO resCreatePaymentDTO = paymentTypeService.convertToResCreatePaymentDTO(newPaymentType);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreatePaymentDTO);
    }

    @GetMapping("/paymentTypes/{id}")
    @ApiMessage(message = "Xem chi tiết loại thanh toán")
    public ResponseEntity<ResGetPaymentDTO> getMethodName(@PathVariable("id") long id) throws IdInvalidException {
        if(paymentTypeService.checkExistId(id) == false) {
            throw new IdInvalidException("Id không tồn tại");
        }
        PaymentType paymentType = paymentTypeService.getPaymentTypeById(id);
        ResGetPaymentDTO resGetPaymentDTO = paymentTypeService.convertToResGetPaymentDTO(paymentType);
        return ResponseEntity.ok().body(resGetPaymentDTO);
    }

    @GetMapping("/paymentTypes")
    @ApiMessage(message = "Xem danh sách loại thanh toán")
    public ResponseEntity<ResPaginationDTO> getAllPaymentTypes(@Filter Specification<PaymentType> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = paymentTypeService.getAllPaymentType(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @PostMapping("/paymentTypes")
    @ApiMessage(message = "Cập nhật loại thanh toán")
    public ResponseEntity<ResUpdatePaymentDTO> updatePaymentTypes(@RequestBody PaymentType paymentType) {
        PaymentType currentPaymentType = paymentTypeService.updatePaymentType(paymentType);
        ResUpdatePaymentDTO resUpdatePaymentDTO = paymentTypeService.convertToResUpdatePaymentDTO(currentPaymentType);
        return ResponseEntity.ok().body(resUpdatePaymentDTO);
    }

    @DeleteMapping("/paymentTypes/{id}")
    @ApiMessage(message = "Xóa loại thanh toán")
    public ResponseEntity<Void> deletePaymentTypes(@PathVariable("id") long id) throws IdInvalidException {
        if(paymentTypeService.checkExistId(id) == false) {
            throw new IdInvalidException("Id không tồn tại");
        }
        paymentTypeService.deletePaymentType(id);
        return ResponseEntity.ok().body(null);
    }
}
