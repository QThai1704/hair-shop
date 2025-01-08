package t221124nqt.ecommerce.hair_shop.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.store.Store;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResCreateStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResGetStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResUpdateStoreDTO;
import t221124nqt.ecommerce.hair_shop.mapper.store.StoreMapper;
import t221124nqt.ecommerce.hair_shop.repository.StoreRepository;
import t221124nqt.ecommerce.hair_shop.service.StoreService;

@Service
@RequiredArgsConstructor
public class IStoreService implements StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public Store createStore(Store store) {
        return this.storeRepository.save(store);
    }

    @Override
    public ResCreateStoreDTO convertResCreateStoreDTO(Store store) {
        ResCreateStoreDTO resCreateStoreDTO = this.storeMapper.toResCreateStoreDTO(store);
        return resCreateStoreDTO;
    }

    @Override
    public Store getStoreById(long storeId) {
        return this.storeRepository.findById(storeId).orElse(null);
    }

    @Override
    public ResGetStoreDTO convertResGetStoreDTO(Store store) {
        ResGetStoreDTO resGetStoreDTO = this.storeMapper.toResGetStoreDTO(store);
        return resGetStoreDTO;
    }

    @Override
    public ResPaginationDTO getAllStore(Specification<Store> spec, Pageable pageable) {
        Page<Store> stores = this.storeRepository.findAll(spec, pageable);

        List<ResGetStoreDTO> resGetStoreDTOList = stores.getContent().stream()
                .map(store -> this.storeMapper.toResGetStoreDTO(store))
                .collect(Collectors.toList());

        ResPaginationDTO resPaginationDTO = new ResPaginationDTO();
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(stores, pageable);
        resPaginationDTO.setMeta(meta);
        resPaginationDTO.setData(resGetStoreDTOList);
        return resPaginationDTO;
    }

    @Override
    public Store updateStore(Store store) {
        Store currentStore = this.getStoreById(store.getId());
        if (currentStore != null) {
            currentStore = this.storeMapper.toStore(store);
            return this.storeRepository.save(currentStore);
        }
        return null;
    }

    @Override
    public ResUpdateStoreDTO convertResUpdateStoreDTO(Store store) {
        ResUpdateStoreDTO resUpdateStoreDTO = this.storeMapper.toResUpdateStoreDTO(store);
        return resUpdateStoreDTO;
    }

    @Override
    public void deleteStore(long id) {
        this.storeRepository.deleteById(id);
    }
}
