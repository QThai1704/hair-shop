package t221124nqt.ecommerce.hair_shop.domain.auth;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    @Column(columnDefinition = "MEDIUMTEXT")
    String password;
    @NotBlank(message = "Tên không được để trống")
    String lastName;
    @NotBlank(message = "Họ không được để trống")
    String firstName;
    @Enumerated(EnumType.STRING)
    GenderEnum gender;
    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com", message = "Email không hợp lệ")
    String email;
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
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
    @Column(columnDefinition = "MEDIUMTEXT")
    String rememberToken;
    String activeCode;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    Timestamp createAt;
    Timestamp updateAt;
    String createdBy;
    String updatedBy;

    // Hibernate
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_has_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<Permission> permissions;

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
