package t221124nqt.ecommerce.hair_shop.domain.product;

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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_categories")
public class ProductCategory {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String categoryCode;
    String categoryName;
    String description;
    String image;
    Timestamp createAt;
    Timestamp updateAt;

    // Hibernate mappings
    @OneToMany(mappedBy = "productCategory")
    List<Product> products;
}
