package t221124nqt.ecommerce.hair_shop.dto.response.store;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResUpdateStoreDTO extends ResStoreDTO {
    Timestamp updateAt;
    String updateBy;
}
