package t221124nqt.ecommerce.hair_shop.dto.response.auth.role;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResRoleDTO {
    String name;
    String displayName;
    String guardName;
    List<PermissionRole> permissions;

    @Getter
    @Setter
    public static class PermissionRole {
        String name;
    }
}
