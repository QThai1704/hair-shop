package t221124nqt.ecommerce.hair_shop.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class ReqRegisterDTO {
    @NotBlank(message = "Tài khoản không được trống!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com", message = "Email không hợp lệ")
    String username;
    @NotBlank(message = "Thông tin không được trống!")
    String lastName;
    @NotBlank(message = "Thông tin không được trống!")
    String firstName;
    @NotBlank(message = "Mật khẩu không được trống!")
    String password;
    @NotBlank(message = "Xác nhận mật khẩu không được trống!")
    String comfirmPassword;
}
