package t221124nqt.ecommerce.hair_shop.dto.response.store;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResStoreDTO {
    long id;
    String storeCode;
    String storeName;
    String description;
    String image;
}
