package t221124nqt.ecommerce.hair_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResCreatePaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResGetPaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResPaymentDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.paymentType.ResUpdatePaymentDTO;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentTypeMapper INSTANCE = Mappers.getMapper( PaymentTypeMapper.class);

    @Mapping(target = "paymentCode", source = "paymentCode")
    @Mapping(target = "paymentName", source = "paymentName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", source = "image")
    ResPaymentDTO toResPaymentDTO(PaymentType paymentType);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    ResCreatePaymentDTO toResCreatePaymentDTO(PaymentType paymentType);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedBy", source = "updatedBy")
    ResGetPaymentDTO toResGetPaymentDTO(PaymentType paymentType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "updatedBy", source = "updatedBy")
    ResUpdatePaymentDTO toResUpdatePaymentDTO(PaymentType paymentType);

    @Mapping(target = "paymentCode", source = "paymentCode")
    @Mapping(target = "paymentName", source = "paymentName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedBy", source = "updatedBy")
    PaymentType toPaymentType(PaymentType PaymentType);
}
