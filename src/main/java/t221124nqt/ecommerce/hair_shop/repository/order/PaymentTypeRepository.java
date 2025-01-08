package t221124nqt.ecommerce.hair_shop.repository.order;

import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.order.PaymentType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
@SuppressWarnings("null")
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>,
        JpaSpecificationExecutor<PaymentType> {
    Page<PaymentType> findAll(Specification<PaymentType> spec, Pageable pageable);

    boolean existsByPaymentName(String paymentName);

    boolean existsById(long id);
}
