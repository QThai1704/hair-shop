package t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResPaymentDTO {
    String paymentCode;
    String paymentName;
    String description;
    String image;
}
