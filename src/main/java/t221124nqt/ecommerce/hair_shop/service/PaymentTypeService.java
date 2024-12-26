package t221124nqt.ecommerce.hair_shop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResUpdatePaymentDTO;

public interface PaymentTypeService {
    PaymentType createPaymentType(PaymentType paymentType);

    ResCreatePaymentDTO convertToResCreatePaymentDTO(PaymentType paymentType);

    PaymentType getPaymentTypeById(long id);

    ResGetPaymentDTO convertToResGetPaymentDTO(PaymentType paymentType);

    ResPaginationDTO getAllPaymentType(Specification<PaymentType> specification, Pageable pageable);

    PaymentType updatePaymentType(PaymentType paymentType);

    ResUpdatePaymentDTO convertToResUpdatePaymentDTO(PaymentType paymentType);

    void deletePaymentType(long id);

    boolean checkExistName(String name);

    boolean checkExistId(long id);
}
