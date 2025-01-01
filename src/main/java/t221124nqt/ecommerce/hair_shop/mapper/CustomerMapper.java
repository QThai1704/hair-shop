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
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    ResCustomerDTO toCustomerDTO(Customer customer);

    ResCreateCustomerDTO toCreateCustomerDTO(Customer customer);

    ResUpdateCustomerDTO toUpdateCustomerDTO(Customer customer);

    ResGetCustomerDTO toGetCustomerDTO(Customer customer);

    Customer toCustomer(Customer customer);
}
