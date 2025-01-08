package t221124nqt.ecommerce.hair_shop.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLoginDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    String password;
}
