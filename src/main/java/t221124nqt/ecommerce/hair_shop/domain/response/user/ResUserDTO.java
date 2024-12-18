package t221124nqt.ecommerce.hair_shop.domain.response.user;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

@Getter
@Setter
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
    String deliveryAddress;
    String city;
    String state;
    String postalCode;
    String country;
    String activeCode;
    StatusEnum status;
    List<RoleUser> roles;
    List<PermissionUser> permissions;

    @Getter
    @Setter
    public static class RoleUser {
        String name;
    }

    @Getter
    @Setter
    public static class PermissionUser {
        String name;
    }
}
