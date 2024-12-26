package t221124nqt.ecommerce.hair_shop.domain.response.customer;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResCustomerDTO {
    String username;
    String lastName;
    String firstName;
    GenderEnum gender;
    String email;
    LocalDate dateOfBirth;
    String avatar;
    String code;
    String company;
    String phone;
    String billingAddress;
    String shippingAddress;
    String city;
    String state;
    String postalCode;
    String country;
    String rememberToken;
    String activeCode;
    StatusEnum status;
}
