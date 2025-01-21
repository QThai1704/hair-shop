package t221124nqt.ecommerce.hair_shop.dto.response.auth.user;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResUserDTO {
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
    List<RoleUser> roles;
    List<PermissionUser> permissions;

    @Getter
    @Setter
    public static class RoleUser {
        private String name;
    }

    @Getter
    @Setter
    public static class PermissionUser {
        private String name;
    }
}
