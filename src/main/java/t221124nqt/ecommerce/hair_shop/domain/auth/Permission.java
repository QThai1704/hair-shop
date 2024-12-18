package t221124nqt.ecommerce.hair_shop.domain.auth;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
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
    String createdBy;
    String updatedBy;

    // Hibernate mappings
    @ManyToMany(mappedBy = "permissions")
    List<Role> roles;

    @ManyToMany(mappedBy = "permissions")
    List<User> users;

    @PrePersist
    public void prePersist() {
        createAt = new Timestamp(System.currentTimeMillis());
        createdBy = "Hệ thống";
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = new Timestamp(System.currentTimeMillis());
        updatedBy = "Hệ thống";
    }
}
