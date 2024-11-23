package t221124nqt.ecommerce.hair_shop.domain.voucher;

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
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String voucherCode;
    String voucherName;
    String description;
    int uses;
    int maxUses;
    int maxUsesUser;
    int type;
    double discountAmount;
    boolean isFixed;
    Timestamp startDate;
    Timestamp endDate;
    Timestamp createAt;
    Timestamp updateAt;
    Timestamp deleteAt;

    // Hibernate mappings
    @OneToMany(mappedBy = "voucher")
    List<ProductVoucher> productVouchers;

    @OneToMany(mappedBy = "voucher")
    List<CustomerVoucher> customerVouchers;
}
