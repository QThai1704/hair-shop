package t221124nqt.ecommerce.hair_shop.domain.store;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "store_imports")
public class StoreImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDateTime importDate;
    Timestamp createAt;
    Timestamp updateAt;

    // Hibernate mappings
    // store_id, staff_id
    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "storeImport")
    List<StoreImportDetail> storeImportDetails;
}
