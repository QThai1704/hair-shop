package t221124nqt.ecommerce.hair_shop.dto.response.auth.user;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResGetUserDTO extends ResUserDTO {
    StatusEnum status;
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;
}
