package t221124nqt.ecommerce.hair_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.order.Customer;
import t221124nqt.ecommerce.hair_shop.domain.response.customer.ResCreateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.customer.ResCustomerDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.customer.ResGetCustomerDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.customer.ResUpdateCustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "company", target = "company")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "billingAddress", target = "billingAddress")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "rememberToken", target = "rememberToken")
    @Mapping(source = "activeCode", target = "activeCode")
    @Mapping(source = "status", target = "status")
    ResCustomerDTO toCustomerDTO(Customer customer);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    ResCreateCustomerDTO toCreateCustomerDTO(Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResUpdateCustomerDTO toUpdateCustomerDTO(Customer customer);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResGetCustomerDTO toGetCustomerDTO(Customer customer);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "company", target = "company")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "billingAddress", target = "billingAddress")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "rememberToken", target = "rememberToken")
    @Mapping(source = "activeCode", target = "activeCode")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedBy", source = "updatedBy")
    Customer toCustomer(Customer customer);
}

