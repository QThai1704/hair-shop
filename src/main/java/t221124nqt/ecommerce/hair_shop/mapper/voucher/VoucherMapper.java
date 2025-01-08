package t221124nqt.ecommerce.hair_shop.mapper.voucher;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.voucher.Voucher;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResCreateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResGetVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResUpdateVoucherDTO;

@Mapper(componentModel = "spring")
@SuppressWarnings("all")
public interface VoucherMapper {
    VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);

    ResCreateVoucherDTO toResCreateVoucherDTO(Voucher voucher);

    ResGetVoucherDTO toResGetVoucherDTO(Voucher voucher);

    ResUpdateVoucherDTO toResUpdateVoucherDTO(Voucher voucher);

    Voucher toVoucher(Voucher voucher);
}
