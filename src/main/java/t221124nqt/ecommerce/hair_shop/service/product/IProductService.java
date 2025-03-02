package t221124nqt.ecommerce.hair_shop.service.product;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.product.Product;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.product.ResProductDTO;
import t221124nqt.ecommerce.hair_shop.exception.IdInvalidException;
import t221124nqt.ecommerce.hair_shop.mapper.product.ProductMapper;
import t221124nqt.ecommerce.hair_shop.repository.product.ProductRepository;
import t221124nqt.ecommerce.hair_shop.service.file.ImpImportFilesService;

@Service
@RequiredArgsConstructor
public class IProductService implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImpImportFilesService importFilesService;

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public ResProductDTO convertResCreateProductDTO(Product product) {
        ResProductDTO resProductDTO = this.productMapper.toResCreateProductDTO(product);
        return resProductDTO;
    }

    @Override
    public Product getProductById(long id) throws IdInvalidException {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Không tồn tại sản phẩm!"));
    }

    @Override
    public ResProductDTO convertResGetProductDTO(Product product) {
        ResProductDTO resProductDTO = this.productMapper.toResGetProductDTO(product);
        return resProductDTO;
    }

    @Override
    public ResPaginationDTO getAllProduct(Specification<Product> spec, Pageable pageable) {
        Page<Product> products = this.productRepository.findAll(spec, pageable);

        List<ResProductDTO> resProductDTOList = products.getContent().stream()
                .map(product -> this.productMapper.toResGetProductDTO(product))
                .collect(Collectors.toList());

        ResPaginationDTO resPaginationDTO = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(products, pageable);
        resPaginationDTO.setMeta(meta);
        resPaginationDTO.setData(resProductDTOList);
        return resPaginationDTO;
    }

    @Override
    public Product updateProduct(Product product) throws IdInvalidException {
        Product currentProduct = this.getProductById(product.getId());
        if (currentProduct != null) {
            return this.productRepository.save(product);
        }
        return null;
    }

    @Override
    public ResProductDTO convertResUpdateProductDTO(Product product) {
        ResProductDTO resProductDTO = this.productMapper.toResUpdateProductDTO(product);
        return resProductDTO;
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public boolean checkExistById(long id) {
        return this.productRepository.existsById(id);
    }

    @Override
    public void saveProductsToDatabase(MultipartFile file) {
        if (importFilesService.isValidExcelFile(file)) {
            try {
                List<Product> products = importFilesService.importFileProductToDb(file.getInputStream(), "Sheet1");
                productRepository.saveAll(products);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
