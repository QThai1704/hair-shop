package t221124nqt.ecommerce.hair_shop.domain.response.paymentType;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResCreatePaymentDTO extends ResPaymentDTO{
    Timestamp createdAt;
    String createdBy;
}
