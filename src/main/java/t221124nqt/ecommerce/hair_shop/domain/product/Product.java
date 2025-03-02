package t221124nqt.ecommerce.hair_shop.domain.product;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.domain.order.OrderDetail;
import t221124nqt.ecommerce.hair_shop.domain.store.StoreExportDetail;
import t221124nqt.ecommerce.hair_shop.domain.store.StoreImportDetail;
import t221124nqt.ecommerce.hair_shop.domain.voucher.VoucherProduct;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {
    public Product() {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String productCode;
    String productName;
    String image;
    String shortDescription;
    @Column(columnDefinition = "MEDIUMTEXT")
    String description;
    // Giá nhập vào
    double standardCost;
    // Giá bán ra
    double listPrice;
    // Số lượng sản phẩm trong 1 đơn vị (24 lon / thùng)
    double quantityPerUnit;
    // Flag hàng nổi bật
    boolean isFeatured;
    // Flag hàng mới
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
