package t221124nqt.ecommerce.hair_shop.controller.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import t221124nqt.ecommerce.hair_shop.domain.order.Customer;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResCreateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResGetCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.order.customer.ResUpdateCustomerDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.order.ICustomerService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    @ApiMessage(message = "Tạo khách hàng")
    public ResponseEntity<ResCreateCustomerDTO> createCustomer(@RequestBody Customer customer) {
        if(this.customerService.checkExistEmail(customer.getEmail())) {
            throw new RuntimeException("Đã tồn tại email: " + customer.getEmail());
        }
        Customer newCustomer = this.customerService.createCustomer(customer);
        ResCreateCustomerDTO resCreateCustomerDTO = this.customerService
                .convertToResCreateCustomerDTO(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateCustomerDTO);
    }

    @GetMapping("/customers/{id}")
    @ApiMessage(message = "Lấy thông tin khách hàng theo id")
    public ResponseEntity<ResGetCustomerDTO> getCustomerById(@PathVariable("id") long id) throws IdInvalidException {
        Customer customer = this.customerService.getCustomerById(id);
        if(customer == null) {
            throw new IdInvalidException("Không tồn tại khách hàng có id: " + id);
        }
        ResGetCustomerDTO resGetCustomerDTO = this.customerService.convertToResGetCustomerDTO(id);
        return ResponseEntity.ok().body(resGetCustomerDTO);
    }

    @GetMapping("/customers")
    @ApiMessage(message = "Lấy danh sách khách hàng")
    public ResponseEntity<ResPaginationDTO> getAllCustomerAllCustomer(@Filter Specification<Customer> spec, Pageable pageable) {
        ResPaginationDTO res = this.customerService.getAllCustomer(spec, pageable);
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/customers")
    @ApiMessage(message = "Cập nhật thông tin khách hàng")
    public ResponseEntity<ResUpdateCustomerDTO> putMethodName(@RequestBody Customer customer) {
        Customer currentCustomer = this.customerService.updateCustomer(customer);
        ResUpdateCustomerDTO resUpdateCustomerDTO = this.customerService.convertToResUpdateCustomerDTO(currentCustomer);
        return ResponseEntity.ok().body(resUpdateCustomerDTO);
    }

    @DeleteMapping("/customers/{id}")
    @ApiMessage(message = "Xóa khách hàng")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(null);
    }
}
