package t221124nqt.ecommerce.hair_shop.dto.response.other;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResGlobalDTO<T> {
    String error;
    Object message;
    int status;
    T data;
}
