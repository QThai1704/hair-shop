package t221124nqt.ecommerce.hair_shop.repository;

import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>,
    JpaSpecificationExecutor<PaymentType> {
    Page<PaymentType> findAll(Specification<PaymentType> spec ,Pageable pageable);
    boolean existsByName(String name);
    boolean existsById(long id);
}
