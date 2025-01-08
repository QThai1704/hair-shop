package t221124nqt.ecommerce.hair_shop.dto.response.order;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResOrderDTO {
    LocalDateTime orderDate;
    LocalDateTime shippedDate;
    String shipName;
    String shipAddress;
    String shipCity;
    String shipState;
    String shipPostalCode;
    String shipCountry;
    String shippingFee;
    LocalDate paiDate;
    UserRes employee;
    CustomerRes customer;
    PaymentTypeRes paymentType;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserRes {
        String firstName;
        String lastName;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CustomerRes {
        String firstName;
        String lastName;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PaymentTypeRes {
        String paymentName;
    }
}
