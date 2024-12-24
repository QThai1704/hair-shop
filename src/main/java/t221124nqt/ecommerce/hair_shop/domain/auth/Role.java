package t221124nqt.ecommerce.hair_shop.domain.auth;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.YesNoConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
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
@SoftDelete(converter = YesNoConverter.class)
@Entity
@Table(name = "roles")
public class Role {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String displayName;
    String guardName;
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;

    // Hibernate mappings
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    List<User> users;

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
        createdBy = "Hệ thống";
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
        updatedBy = "Hệ thống";
    }
}
