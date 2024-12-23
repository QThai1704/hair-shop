package t221124nqt.ecommerce.hair_shop.domain.response.user;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateUserDTO extends ResUserDTO {
    long id;
    Timestamp updatedAt;
    String updatedBy;
}
