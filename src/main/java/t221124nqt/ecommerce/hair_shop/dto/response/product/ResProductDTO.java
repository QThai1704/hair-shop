package t221124nqt.ecommerce.hair_shop.dto.response.product;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResProductDTO {
    long id;
    String productCode;
    String productName;
    String image;
    String shortDescription;
    String description;
    double standardCost;
    double listPrice;
    double quantityPerUnit;
    boolean isFeatured;
    boolean isNew;
    Timestamp createdAt;
    Timestamp updatedAt;
}
