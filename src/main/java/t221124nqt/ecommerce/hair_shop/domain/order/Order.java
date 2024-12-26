package t221124nqt.ecommerce.hair_shop.domain.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDateTime orderDate;
    LocalDateTime shippedDate;
    String shipName;
    String shipAddress;
    String shipCity;
    String shipState;
    String shipPostalCode;
    String shipCountry;
    String shippingFee;
    LocalDate paiDate;
    String orderStatus;
    Timestamp createdAt;
    Timestamp updatedAt;

    // Hibernate mappings
    @ManyToOne
    @JoinColumn(name = "employee_id")
    User employee;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    PaymentType paymentType;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
