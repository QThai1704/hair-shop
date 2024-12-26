package t221124nqt.ecommerce.hair_shop.domain.order;

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
@Table(name = "payment_types")
public class PaymentType {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String paymentCode;
    String paymentName;
    String description;
    String image;
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;

    // Hibernate mappings
    @OneToMany(mappedBy = "paymentType")
    List<Order> orders;
}
