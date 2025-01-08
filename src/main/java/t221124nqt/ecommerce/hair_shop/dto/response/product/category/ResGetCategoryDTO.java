package t221124nqt.ecommerce.hair_shop.dto.response.product.category;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResGetCategoryDTO extends ResCategoryDTO {
    Timestamp createAt;
    Timestamp updateAt;
}
