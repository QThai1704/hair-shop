package t221124nqt.ecommerce.hair_shop.dto.response.order;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResUpdateOrderDTO extends ResOrderDTO {
    long id;
    Timestamp updatedAt;
}
