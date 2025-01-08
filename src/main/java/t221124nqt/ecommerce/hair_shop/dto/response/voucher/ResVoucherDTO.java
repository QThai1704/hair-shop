package t221124nqt.ecommerce.hair_shop.dto.response.voucher;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResVoucherDTO {
    long id;
    String voucherCode;
    String voucherName;
    String description;
    int uses;
    int maxUses;
    int maxUsesUser;
    int type;
    double discountAmount;
    boolean isFixed;
    Timestamp startDate;
    Timestamp endDate;
    Timestamp deleteAt;
}
