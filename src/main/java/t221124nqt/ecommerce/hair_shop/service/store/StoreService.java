package t221124nqt.ecommerce.hair_shop.service.store;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.store.Store;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResCreateStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResGetStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResUpdateStoreDTO;

public interface StoreService {
    Store createStore(Store store);

    ResCreateStoreDTO convertResCreateStoreDTO(Store store);

    Store getStoreById(long storeId);

    ResGetStoreDTO convertResGetStoreDTO(Store store);

    ResPaginationDTO getAllStore(Specification<Store> spec, Pageable pageable);

    Store updateStore(Store store);

    ResUpdateStoreDTO convertResUpdateStoreDTO(Store store);

    void deleteStore(long storeId);
}
