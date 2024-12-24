package t221124nqt.ecommerce.hair_shop.domain.response.permission;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResUpdatePermissionDTO extends ResPermissionDTO{
    long id;
    Timestamp updatedAt;
    String updatedBy;
}
