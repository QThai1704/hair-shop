package t221124nqt.ecommerce.hair_shop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResUpdatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;

public interface PaymentTypeService {
    PaymentType createPaymentType(PaymentType paymentType);

    ResCreatePaymentDTO convertToResCreatePaymentDTO(PaymentType paymentType);

    PaymentType getPaymentTypeById(long id);

    ResGetPaymentDTO convertToResGetPaymentDTO(PaymentType paymentType);

    ResPaginationDTO getAllPaymentType(Specification<PaymentType> specification, Pageable pageable);

    PaymentType updatePaymentType(PaymentType paymentType);

    ResUpdatePaymentDTO convertToResUpdatePaymentDTO(PaymentType paymentType);

    void deletePaymentType(long id);

    boolean checkExistName(String paymentName);

    boolean checkExistId(long id);
}
