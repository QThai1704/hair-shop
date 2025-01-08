package t221124nqt.ecommerce.hair_shop.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.product.Product;
import t221124nqt.ecommerce.hair_shop.dto.response.product.ResProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ResProductDTO toResCreateProductDTO(Product product);

    ResProductDTO toResGetProductDTO(Product product);

    @Mapping(target = "createdAt", ignore = true)
    ResProductDTO toResUpdateProductDTO(Product product);
}
