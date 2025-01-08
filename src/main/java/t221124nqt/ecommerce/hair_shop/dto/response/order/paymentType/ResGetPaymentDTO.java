package t221124nqt.ecommerce.hair_shop.dto.response.order.paymentType;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResGetPaymentDTO extends ResPaymentDTO{
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;
}
