package t221124nqt.ecommerce.hair_shop.service.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.product.Product;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.product.ResProductDTO;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

public interface ProductService {
    Product createProduct(Product product);

    ResProductDTO convertResCreateProductDTO(Product product);

    Product getProductById(long id) throws IdInvalidException;

    ResProductDTO convertResGetProductDTO(Product product);

    ResPaginationDTO getAllProduct(Specification<Product> spec, Pageable pageable);

    Product updateProduct(Product product) throws IdInvalidException;

    ResProductDTO convertResUpdateProductDTO(Product product);

    void deleteProduct(long id);

    boolean checkExistById(long id);
}
