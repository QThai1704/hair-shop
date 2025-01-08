package t221124nqt.ecommerce.hair_shop.dto.response.product.supplier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResSupplierDTO {
    long id;
    String suppliersCode;
    String suppliersName;
    String description;
    String image;
}
