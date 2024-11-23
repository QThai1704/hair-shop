package t221124nqt.ecommerce.hair_shop.domain.voucher;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.domain.order.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customer_vouchers")
public class CustomerVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    Timestamp createAt;
    Timestamp updateAt;

    // Hibernate mappings
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}
