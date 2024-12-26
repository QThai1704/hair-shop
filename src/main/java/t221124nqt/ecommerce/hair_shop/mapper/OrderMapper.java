package t221124nqt.ecommerce.hair_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public class OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class);
}
