package t221124nqt.ecommerce.hair_shop.mapper.store;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.store.Store;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResCreateStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResGetStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResUpdateStoreDTO;

@Mapper(componentModel = "spring")
@SuppressWarnings("all")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "id", ignore = true)
    ResCreateStoreDTO toResCreateStoreDTO(Store store);

    ResUpdateStoreDTO toResUpdateStoreDTO(Store store);

    ResGetStoreDTO toResGetStoreDTO(Store store);

    Store toStore(Store store);
}
