package t221124nqt.ecommerce.hair_shop.domain.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResLoginDTO {
    @JsonProperty("access_token")
    String accessToken;
    Object User;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginedUser {
        private long id;
        private String username;
        private String email;
        private String role;
    }
}
