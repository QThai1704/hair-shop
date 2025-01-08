package t221124nqt.ecommerce.hair_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.product.ProductCategory;

@Repository
public interface ProductCategoryRepository
        extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<ProductCategory> {

}
