package t221124nqt.ecommerce.hair_shop.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.product.ProductSupplier;

@Repository
public interface ProductSupplierRepository
        extends JpaRepository<ProductSupplier, Long>, JpaSpecificationExecutor<ProductSupplier> {

}
