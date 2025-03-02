package t221124nqt.ecommerce.hair_shop.controller.store;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.store.Store;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResCreateStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResGetStoreDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.store.ResUpdateStoreDTO;
import t221124nqt.ecommerce.hair_shop.service.store.IStoreService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final IStoreService storeService;

    @PostMapping("/stores")
    @ApiMessage(message = "Tạo một kho hàng mới")
    public ResponseEntity<ResCreateStoreDTO> createStore(@Valid @RequestBody Store store) {
        Store newStore = this.storeService.createStore(store);
        ResCreateStoreDTO resCreateStoreDTO = this.storeService.convertResCreateStoreDTO(newStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateStoreDTO);
    }

    @GetMapping("/stores/{id}")
    @ApiMessage(message = "Tìm kiếm kho hàng")
    public ResponseEntity<ResGetStoreDTO> getStoreById(@PathVariable("id") long id) {
        Store findStoreById = this.storeService.getStoreById(id);
        ResGetStoreDTO resGetStoreDTO = this.storeService.convertResGetStoreDTO(findStoreById);
        return ResponseEntity.ok().body(resGetStoreDTO);
    }

    @GetMapping("/stores")
    @ApiMessage(message = "Lấy tất cả danh sách của kho hàng")
    public ResponseEntity<ResPaginationDTO> fetchAllStore(@Filter Specification<Store> spec, Pageable pageable) {
        return ResponseEntity.ok().body(this.storeService.getAllStore(spec, pageable));
    }

    @PutMapping("/stores")
    @ApiMessage(message = "Cập nhật thông tin kho hàng")
    public ResponseEntity<ResUpdateStoreDTO> updateStore(@Valid @RequestBody Store store) {
        Store updateStore = this.storeService.updateStore(store);
        ResUpdateStoreDTO resGetStoreDTO = this.storeService.convertResUpdateStoreDTO(updateStore);
        return ResponseEntity.ok().body(resGetStoreDTO);
    }

    @DeleteMapping("/stores/{id}")
    @ApiMessage(message = "Xóa kho hàng")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") long id) {
        this.storeService.deleteStore(id);
        return ResponseEntity.ok().body(null);
    }
}
