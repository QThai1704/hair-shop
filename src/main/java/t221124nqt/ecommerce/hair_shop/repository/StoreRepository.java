package t221124nqt.ecommerce.hair_shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.store.Store;

@Repository
@SuppressWarnings("null")
public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {
    Page<Store> findAll(Specification<Store> spec, Pageable pageable);
}
