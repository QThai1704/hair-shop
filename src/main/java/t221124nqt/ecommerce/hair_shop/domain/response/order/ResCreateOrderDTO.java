package t221124nqt.ecommerce.hair_shop.domain.response.order;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResCreateOrderDTO extends ResOrderDTO{
    Timestamp createdAt;
    String createdBy;
}
