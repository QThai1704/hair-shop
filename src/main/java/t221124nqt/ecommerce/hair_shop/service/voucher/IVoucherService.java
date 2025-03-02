package t221124nqt.ecommerce.hair_shop.service.voucher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.voucher.Voucher;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResCreateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResGetVoucherDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.voucher.ResUpdateVoucherDTO;
import t221124nqt.ecommerce.hair_shop.mapper.voucher.VoucherMapper;
import t221124nqt.ecommerce.hair_shop.repository.voucher.VoucherRepository;

@Service
@RequiredArgsConstructor
public class IVoucherService implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;

    @Override
    public Voucher createVoucher(Voucher voucher) {
        return this.voucherRepository.save(voucher);
    }

    @Override
    public ResCreateVoucherDTO convertResCreateVoucherDTO(Voucher voucher) {
        ResCreateVoucherDTO resCreateVoucherDTO = this.voucherMapper.toResCreateVoucherDTO(voucher);
        return resCreateVoucherDTO;
    }

    @Override
    public Voucher getVoucherById(long id) {
        return this.voucherRepository.findById(id).orElse(null);
    }

    @Override
    public ResGetVoucherDTO convertResGetVoucherDTO(Voucher voucher) {
        ResGetVoucherDTO resGetVoucherDTO = this.voucherMapper.toResGetVoucherDTO(voucher);
        return resGetVoucherDTO;
    }

    @Override
    public ResPaginationDTO getAllVoucher(Specification<Voucher> spec, Pageable pageable) {
        Page<Voucher> vouchers = this.voucherRepository.findAll(spec, pageable);

        List<ResGetVoucherDTO> resGetVoucherDTOList = vouchers.getContent().stream()
                .map(voucher -> this.voucherMapper.toResGetVoucherDTO(voucher))
                .collect(Collectors.toList());

        ResPaginationDTO resPaginationDTO = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(vouchers, pageable);
        resPaginationDTO.setMeta(meta);
        resPaginationDTO.setData(resGetVoucherDTOList);
        return resPaginationDTO;
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        Voucher currentVoucher = this.getVoucherById(voucher.getId());
        if (currentVoucher != null) {
            currentVoucher = this.voucherMapper.toVoucher(voucher);
            return this.voucherRepository.save(currentVoucher);
        }
        return null;
    }

    @Override
    public ResUpdateVoucherDTO convertResUpdateVoucherDTO(Voucher voucher) {
        ResUpdateVoucherDTO resUpdateVoucherDTO = this.voucherMapper.toResUpdateVoucherDTO(voucher);
        return resUpdateVoucherDTO;
    }

    @Override
    public void deleteVoucher(long id) {
        this.voucherRepository.deleteById(id);
    }
}
