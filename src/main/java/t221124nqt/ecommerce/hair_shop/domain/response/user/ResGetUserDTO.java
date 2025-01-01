package t221124nqt.ecommerce.hair_shop.domain.response.user;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

@Getter
@Setter
public class ResGetUserDTO extends ResUserDTO {
    StatusEnum status;
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;
}
