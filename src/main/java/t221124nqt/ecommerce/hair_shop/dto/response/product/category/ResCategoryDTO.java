package t221124nqt.ecommerce.hair_shop.dto.response.product.category;

import java.sql.Timestamp;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResCategoryDTO {
    long id;
    String categoryCode;
    String categoryName;
    String description;
    String image;
    Timestamp createAt;
    Timestamp updateAt;
    List<ProductCategory> products;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ProductCategory {
        String name;
    }
}
