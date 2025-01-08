package t221124nqt.ecommerce.hair_shop.dto.response.store.imports;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.store.StoreImportDetail;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResStoreImportDTO {
    long id;
    LocalDateTime importDate;
    Timestamp createAt;
    Timestamp updateAt;
    List<StoreImportDetail> storeImportDetails;
}
