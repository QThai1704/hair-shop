package t221124nqt.ecommerce.hair_shop.domain.product;

import java.sql.Timestamp;
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
import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.domain.store.StoreExportDetail;
import t221124nqt.ecommerce.hair_shop.domain.store.StoreImportDetail;
import t221124nqt.ecommerce.hair_shop.domain.voucher.VoucherProduct;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String productCode;
    String productName;
    String image;
    String shortDescription;
    String description;
    double standardCost;
    double listPrice;
    double quantityPerUnit;
    long discontinued;
    boolean isFeatured;
    boolean isNew;
    Timestamp createdAt;
    Timestamp updatedAt;

    // Hibernate mappings
    @ManyToOne
    @JoinColumn(name = "category_id")
    ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    ProductSupplier productSupplier;

    @OneToMany(mappedBy = "product")
    List<ProductReview> productReviews;

    @OneToMany(mappedBy = "product")
    List<ProductImage> productImages;

    @OneToMany(mappedBy = "product")
    List<ProductDiscount> productDiscounts;

    @OneToMany(mappedBy = "product")
    List<StoreImportDetail> storeImportDetails;

    @OneToMany(mappedBy = "product")
    List<StoreExportDetail> storeExportDetails;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    List<VoucherProduct> productVouchers;
}
