package t221124nqt.ecommerce.hair_shop.dto.request.auth.user;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqUpdateUserDTO {
    long id;
    String username;
    String lastName;
    String firstName;
    GenderEnum gender;
    String email;
    LocalDate dateOfBirth;
    String avatar;
    String code;
    String jobTitle;
    String department;
    String phone;
    String address;
    String city;
    String state;
    String country;
    String activeCode;
    List<Role> roles;
    List<Permission> permissions;
}
