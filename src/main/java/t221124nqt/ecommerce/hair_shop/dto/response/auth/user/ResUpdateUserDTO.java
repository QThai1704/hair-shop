package t221124nqt.ecommerce.hair_shop.dto.response.auth.user;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResUpdateUserDTO extends ResUserDTO {
    long id;
    Timestamp updatedAt;
    String updatedBy;
}
