package t221124nqt.ecommerce.hair_shop.controller.product;

import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.domain.product.Product;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.product.ResProductDTO;
import t221124nqt.ecommerce.hair_shop.exception.IdInvalidException;
import t221124nqt.ecommerce.hair_shop.service.product.IProductService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
    private final IProductService productService;

    @PostMapping("/products")
    @ApiMessage(message = "Tạo sản phẩm mới")
    public ResponseEntity<ResProductDTO> createProduct(@RequestBody Product product) {
        Product newProduct = this.productService.createProduct(product);
        ResProductDTO resProductDTO = this.productService.convertResCreateProductDTO(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(resProductDTO);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResProductDTO> getProductById(@PathVariable("id") long id) throws IdInvalidException {
        if (!this.productService.checkExistById(id)) {
            throw new IdInvalidException("Không tồn tại sản phẩm!");
        }
        Product findProductById = this.productService.getProductById(id);
        ResProductDTO resProductDTO = this.productService.convertResGetProductDTO(findProductById);
        return ResponseEntity.status(HttpStatus.CREATED).body(resProductDTO);
    }

    @GetMapping("/products")
    @ApiMessage(message = "Danh sách sản phẩm")
    public ResponseEntity<ResPaginationDTO> getAllProduct(@Filter Specification<Product> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = this.productService.getAllProduct(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @PutMapping("/products")
    @ApiMessage(message = "Cập nhật sản phẩm")
    public ResponseEntity<ResProductDTO> updateProduct(@RequestBody Product product) throws IdInvalidException {
        Product updateProduct = this.productService.updateProduct(product);
        ResProductDTO resProductDTO = this.productService.convertResUpdateProductDTO(updateProduct);
        return ResponseEntity.ok().body(resProductDTO);
    }

    @DeleteMapping("/products/{id}")
    @ApiMessage(message = "Xóa sản phẩm")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) throws IdInvalidException {
        if (!this.productService.checkExistById(id)) {
            throw new IdInvalidException("Không tồn tại sản phẩm!");
        }
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().body(null);
    }
}
