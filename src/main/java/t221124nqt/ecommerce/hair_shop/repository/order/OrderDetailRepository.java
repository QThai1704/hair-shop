package t221124nqt.ecommerce.hair_shop.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;

@Repository
@SuppressWarnings("null")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>,
        JpaSpecificationExecutor<OrderDetail> {
    Page<OrderDetail> findAll(Specification<OrderDetail> spec, Pageable pageable);

    boolean existsById(long id);
}
