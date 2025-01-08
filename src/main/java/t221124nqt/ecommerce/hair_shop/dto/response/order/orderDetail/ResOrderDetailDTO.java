package t221124nqt.ecommerce.hair_shop.dto.response.order.orderDetail;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResOrderDetailDTO {
    long id;
    long quantity;
    double unitPrice;
    float discountPercentage;
    float discountAmount;
    String orderDetailStatus;
    LocalDateTime dateAllocated;
    OrderRes orders;
    ProductRes products;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrderRes {
        String shipName;
        String shipAddress;
        String shipCity;
        String shipState;
        String shipPostalCode;
        String shipCountry;
        String shippingFee;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ProductRes {
        String name;
    }

}
