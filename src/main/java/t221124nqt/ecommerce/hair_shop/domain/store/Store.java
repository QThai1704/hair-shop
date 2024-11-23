package t221124nqt.ecommerce.hair_shop.domain.store;

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
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String storeCode;
    String storeName;
    String description;
    String image;
    Timestamp createAt;
    Timestamp updateAt;
    String createBy;
    String updateBy;

    // Hibernate mappings
    @OneToMany(mappedBy = "store")
    List<StoreExport> storeExports;

    @OneToMany(mappedBy = "store")
    List<StoreImport> storeImports;
}
