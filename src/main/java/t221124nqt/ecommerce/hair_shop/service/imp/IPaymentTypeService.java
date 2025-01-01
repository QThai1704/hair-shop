package t221124nqt.ecommerce.hair_shop.service.imp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResUpdatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.mapper.PaymentTypeMapper;
import t221124nqt.ecommerce.hair_shop.repository.PaymentTypeRepository;
import t221124nqt.ecommerce.hair_shop.service.PaymentTypeService;

@Service
public class IPaymentTypeService implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    public IPaymentTypeService(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    @Override
    public PaymentType createPaymentType(PaymentType paymentType) {
        return this.paymentTypeRepository.save(paymentType);
    }

    @Override
    public ResCreatePaymentDTO convertToResCreatePaymentDTO(PaymentType paymentType) {
        return this.paymentTypeMapper.toResCreatePaymentDTO(paymentType);
    }

    @Override
    public PaymentType getPaymentTypeById(long id) {
        Optional<PaymentType> paymentType = this.paymentTypeRepository.findById(id);
        if (paymentType.isPresent()) {
            return paymentType.get();
        }
        return null;
    }

    @Override
    public ResGetPaymentDTO convertToResGetPaymentDTO(PaymentType paymentType) {
        return this.paymentTypeMapper.toResGetPaymentDTO(paymentType);
    }

    @Override
    public ResPaginationDTO getAllPaymentType(Specification<PaymentType> specification, Pageable pageable) {
        Page<PaymentType> paymentTypesPage = this.paymentTypeRepository.findAll(specification, pageable);
        List<ResGetPaymentDTO> paymentTypes = paymentTypesPage.getContent()
                .stream()
                .map(paymentType -> this.paymentTypeMapper.toResGetPaymentDTO(paymentType))
                .collect(Collectors.toList());
        ResPaginationDTO resPaginationDTO = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(paymentTypesPage, pageable);
        resPaginationDTO.setMeta(meta);
        resPaginationDTO.setData(paymentTypes);
        return resPaginationDTO;
    }

    @Override
    public PaymentType updatePaymentType(PaymentType paymentType) {
        PaymentType currentPaymentType = this.getPaymentTypeById(paymentType.getId());
        if (currentPaymentType != null) {
            Timestamp createdAt = currentPaymentType.getCreatedAt();
            String createdBy = currentPaymentType.getCreatedBy();
            currentPaymentType = this.paymentTypeMapper.toPaymentType(paymentType);
            currentPaymentType.setCreatedAt(createdAt);
            currentPaymentType.setCreatedBy(createdBy);
            return this.paymentTypeRepository.save(currentPaymentType);
        }
        return null;
    }

    @Override
    public ResUpdatePaymentDTO convertToResUpdatePaymentDTO(PaymentType paymentType) {
        return this.paymentTypeMapper.toResUpdatePaymentDTO(paymentType);
    }

    @Override
    public void deletePaymentType(long id) {
        this.paymentTypeRepository.deleteById(id);
    }

    @Override
    public boolean checkExistName(String name) {
        return this.paymentTypeRepository.existsByPaymentName(name);
    }

    @Override
    public boolean checkExistId(long id) {
        return this.paymentTypeRepository.existsById(id);
    }

}
