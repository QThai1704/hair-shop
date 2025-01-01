package t221124nqt.ecommerce.hair_shop.domain.request.user;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqCreateUserDTO {
    String username;
    String lastName;
    String firstName;
    String password;
    GenderEnum gender;
    String email;
    LocalDate dateOfBirth;
    String avatar;
    String code;
    String jobTitle;
    String department;
    String phone;
    String address;
    String deliveryAddress;
    String city;
    String state;
    String postalCode;
    String country;
    String activeCode;
}
