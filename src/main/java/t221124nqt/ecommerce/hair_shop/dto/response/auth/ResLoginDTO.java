package t221124nqt.ecommerce.hair_shop.dto.response.auth;

import java.util.List;

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
    public static class LoginUser {
        private long id;
        private String username;
        private String email;
        private List<RoleUser> role;
    }

    @Getter
    @Setter
    public static class RoleUser {
        private String name;
    }
}
