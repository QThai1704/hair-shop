package t221124nqt.ecommerce.hair_shop.repository.order;

import t221124nqt.ecommerce.hair_shop.domain.order.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("null")
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {
    boolean existsByEmail(String email);

    boolean existsById(long id);

    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);
}
