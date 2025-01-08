package t221124nqt.ecommerce.hair_shop.service.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.order.Customer;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResCreateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResGetCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResUpdateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    ResCreateCustomerDTO convertToResCreateCustomerDTO(Customer customer);

    Customer getCustomerById(long id);

    ResGetCustomerDTO convertToResGetCustomerDTO(long id);

    ResPaginationDTO getAllCustomer(Specification<Customer> spec, Pageable pageable);

    Customer updateCustomer(Customer customer);

    ResUpdateCustomerDTO convertToResUpdateCustomerDTO(Customer customer);

    void deleteCustomer(long id);

    Customer getCustomerByEmail(String email);

    boolean checkExistEmail(String email);

    boolean checkExistId(long id);
}