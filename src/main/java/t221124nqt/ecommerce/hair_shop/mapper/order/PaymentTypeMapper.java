package t221124nqt.ecommerce.hair_shop.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResPaymentDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType.ResUpdatePaymentDTO;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentTypeMapper INSTANCE = Mappers.getMapper(PaymentTypeMapper.class);

    ResPaymentDTO toResPaymentDTO(PaymentType paymentType);

    ResCreatePaymentDTO toResCreatePaymentDTO(PaymentType paymentType);

    ResGetPaymentDTO toResGetPaymentDTO(PaymentType paymentType);

    ResUpdatePaymentDTO toResUpdatePaymentDTO(PaymentType paymentType);

    PaymentType toPaymentType(PaymentType PaymentType);
}
