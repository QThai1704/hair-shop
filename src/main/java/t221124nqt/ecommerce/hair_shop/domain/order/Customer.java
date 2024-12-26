package t221124nqt.ecommerce.hair_shop.domain.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.YesNoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
import t221124nqt.ecommerce.hair_shop.domain.product.ProductReview;
import t221124nqt.ecommerce.hair_shop.domain.voucher.CustomerVoucher;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete(converter = YesNoConverter.class)
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "Tên đăng nhập không để trống")
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
    String company;
    String phone;
    String billingAddress;
    String shippingAddress;
    String city;
    String state;
    String postalCode;
    String country;
    @Column(columnDefinition = "MEDIUMTEXT")
    String rememberToken;
    String activeCode;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;

    // Hibernate mappings
    @OneToMany(mappedBy = "customer")
    List<Order> orders;

    @OneToMany(mappedBy = "customer")
    List<ProductReview> productReviews;

    @OneToMany(mappedBy = "customer")
    List<CustomerVoucher> customerVouchers;

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
