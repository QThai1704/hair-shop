package t221124nqt.ecommerce.hair_shop.service.voucher;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.voucher.Voucher;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResCreateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResGetVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResUpdateVoucherDTO;

public interface VoucherService {
    Voucher createVoucher(Voucher voucher);

    ResCreateVoucherDTO convertResCreateVoucherDTO(Voucher voucher);

    Voucher getVoucherById(long id);

    ResGetVoucherDTO convertResGetVoucherDTO(Voucher voucher);

    ResPaginationDTO getAllVoucher(Specification<Voucher> spec, Pageable pageable);

    Voucher updateVoucher(Voucher voucher);

    ResUpdateVoucherDTO convertResUpdateVoucherDTO(Voucher voucher);

    void deleteVoucher(long id);
}
