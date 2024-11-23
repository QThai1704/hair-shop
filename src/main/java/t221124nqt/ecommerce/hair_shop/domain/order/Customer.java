package t221124nqt.ecommerce.hair_shop.domain.order;

import java.sql.Timestamp;
import java.time.LocalDate;
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
import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;
import t221124nqt.ecommerce.hair_shop.domain.product.ProductReview;
import t221124nqt.ecommerce.hair_shop.domain.voucher.CustomerVoucher;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    String lastName;
    String firstName;
    GenderEnum gender;
    String email;
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
    String rememberToken;
    String activeCode;
    StatusEnum status;
    Timestamp createAt;
    Timestamp updateAt;

    // Hibernate mappings
    @OneToMany(mappedBy = "customer")
    List<Order> orders;

    @OneToMany(mappedBy = "customer")
    List<ProductReview> productReviews;

    @OneToMany(mappedBy = "customer")
    List<CustomerVoucher> customerVouchers;
}
