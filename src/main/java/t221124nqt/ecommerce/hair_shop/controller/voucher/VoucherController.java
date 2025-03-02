package t221124nqt.ecommerce.hair_shop.controller.voucher;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.voucher.Voucher;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResCreateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResGetVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResUpdateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.service.voucher.IVoucherService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class VoucherController {
    private final IVoucherService voucherService;

    @PostMapping("/vouchers")
    @ApiMessage(message = "Thêm mới mã giảm giá")
    public ResponseEntity<ResCreateVoucherDTO> createVoucher(@RequestBody Voucher voucher) {
        Voucher newVoucher = this.voucherService.createVoucher(voucher);
        ResCreateVoucherDTO resCreateVoucherDTO = this.voucherService.convertResCreateVoucherDTO(newVoucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateVoucherDTO);
    }

    @GetMapping("/vouchers/{id}")
    @ApiMessage(message = "Lấy thông tin mã giảm giá theo id")
    public ResponseEntity<ResGetVoucherDTO> getMethodName(@PathVariable("id") long id) {
        Voucher voucher = this.voucherService.getVoucherById(id);
        ResGetVoucherDTO resGetVoucherDTO = this.voucherService.convertResGetVoucherDTO(voucher);
        return ResponseEntity.ok().body(resGetVoucherDTO);
    }

    @GetMapping("/vouchers")
    @ApiMessage(message = "Lấy danh sách mã giảm giá")
    public ResponseEntity<ResPaginationDTO> getAllVoucher(@Filter Specification<Voucher> spec, Pageable pageable) {
        return ResponseEntity.ok().body(this.voucherService.getAllVoucher(spec, pageable));
    }

    @PutMapping("/vouchers")
    @ApiMessage(message = "Cập nhật mã giảm giá")
    public ResponseEntity<ResUpdateVoucherDTO> updateVoucher(@RequestBody Voucher voucher) {
        Voucher updatedVoucher = this.voucherService.updateVoucher(voucher);
        ResUpdateVoucherDTO resUpdateVoucherDTO = this.voucherService.convertResUpdateVoucherDTO(updatedVoucher);
        return ResponseEntity.ok().body(resUpdateVoucherDTO);
    }

    @DeleteMapping("/vouchers/{id}")
    @ApiMessage(message = "Xóa mã giảm giá")
    public ResponseEntity<Void> deleteVoucher(@PathVariable("id") long id) {
        this.voucherService.deleteVoucher(id);
        return ResponseEntity.ok().body(null);
    }
}
