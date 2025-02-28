package t221124nqt.ecommerce.hair_shop.dto.response.other;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResGlobalDTO<T> {
    String error;
    Object message;
    int status;
    T data;
}
