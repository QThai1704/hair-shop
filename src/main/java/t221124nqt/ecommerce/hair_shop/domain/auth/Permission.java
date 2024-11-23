package t221124nqt.ecommerce.hair_shop.domain.auth;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "permissions")
public class Permission {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String displayName;
    String guardName;
    Timestamp createAt;
    Timestamp updateAt;

    // Hibernate mappings
    @OneToMany(mappedBy = "permission")
    List<UserHasPermission> userHasPermissions;

    @OneToMany(mappedBy = "permission")
    List<RoleHasPermissions> roleHasPermissions;
}
