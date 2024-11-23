package t221124nqt.ecommerce.hair_shop.domain.store;

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
import t221124nqt.ecommerce.hair_shop.domain.product.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "store_export_details")
public class StoreExportDetail {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long quantity;
    double unit_price;

    // Hibernate mappings
    // export_id, product_id
    @ManyToOne
    @JoinColumn(name = "store_export_id")
    StoreExport storeExport;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
