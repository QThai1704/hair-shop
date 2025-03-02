package t221124nqt.ecommerce.hair_shop.service.order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.order.Customer;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResCreateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResGetCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResUpdateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.mapper.order.CustomerMapper;
import t221124nqt.ecommerce.hair_shop.repository.order.CustomerRepository;

@Service
public class ICustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public ICustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public ResCreateCustomerDTO convertToResCreateCustomerDTO(Customer customer) {
        ResCreateCustomerDTO resCreateCustomerDTO = this.customerMapper.toCreateCustomerDTO(customer);
        return resCreateCustomerDTO;
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    @Override
    public ResGetCustomerDTO convertToResGetCustomerDTO(long id) {
        Customer customer = this.getCustomerById(id);
        if (customer != null) {
            ResGetCustomerDTO resGetCustomerDTO = this.customerMapper.toGetCustomerDTO(customer);
            return resGetCustomerDTO;
        }
        return null;
    }

    @Override
    public ResPaginationDTO getAllCustomer(Specification<Customer> spec, Pageable pageable) {
        Page<Customer> customers = this.customerRepository.findAll(spec, pageable);
        List<ResGetCustomerDTO> customerList = customers.getContent()
                .stream()
                .map(customer -> this.customerMapper.toGetCustomerDTO(customer))
                .collect(Collectors.toList());
        ResPaginationDTO res = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(customers, pageable);
        res.setData(customerList);
        res.setMeta(meta);
        return res;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer currentCustomer = this.getCustomerById(customer.getId());
        if (currentCustomer != null) {
            Timestamp createdAt = currentCustomer.getCreatedAt();
            String createdBy = currentCustomer.getCreatedBy();
            currentCustomer = this.customerMapper.toCustomer(customer);
            currentCustomer.setCreatedAt(createdAt);
            currentCustomer.setCreatedBy(createdBy);
            return this.customerRepository.save(currentCustomer);
        }
        return null;
    }

    @Override
    public ResUpdateCustomerDTO convertToResUpdateCustomerDTO(Customer customer) {
        Customer currentCustomer = this.updateCustomer(customer);
        ResUpdateCustomerDTO resUpdateCustomerDTO = this.customerMapper.toUpdateCustomerDTO(currentCustomer);
        return resUpdateCustomerDTO;
    }

    @Override
    public void deleteCustomer(long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return this.getCustomerByEmail(email);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return this.customerRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistId(long id) {
        return this.customerRepository.existsById(id);
    }
}
