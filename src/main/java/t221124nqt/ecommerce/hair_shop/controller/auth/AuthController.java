package t221124nqt.ecommerce.hair_shop.controller.auth;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.ReqLoginDTO;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.ReqRegisterDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.ResLoginDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.auth.IUserService;
import t221124nqt.ecommerce.hair_shop.util.SecurityUtil;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.CommonException;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthController {
    @Value("${hair-shop.jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshExpiration;

    private final IUserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;

    public AuthController(
            IUserService userService,
            AuthenticationManagerBuilder authenticationManagerBuilder,
            SecurityUtil securityUtil) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/auth/register")
    @ApiMessage(message = "Tạo tài khoản người dùng")
    public ResponseEntity<ResCreateUserDTO> postRegisterUser(@Valid @RequestBody ReqRegisterDTO reqRegisterDTO)
            throws EmailException, CommonException {
        if (this.userService.checkExistEmail(reqRegisterDTO.getUsername()) == true) {
            throw new EmailException("Tài khoản đã tồn tại!");
        } else {
            if (reqRegisterDTO.getPassword().equals(reqRegisterDTO.getComfirmPassword()) == false) {
                throw new CommonException("Mật khẩu không khớp");
            }
        }
        ResCreateUserDTO resCreateUserDTO = this.userService.registerUser(reqRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateUserDTO);
    }

    @PostMapping("/auth/login")
    @ApiMessage(message = "Đăng nhập")
    public ResponseEntity<ResLoginDTO> postLogin(@Valid @RequestBody ReqLoginDTO reqLoginDTO) throws EmailException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                reqLoginDTO.getUsername(), reqLoginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authentication contextAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (contextAuthentication != null) {
            log.info("Authentication in SecurityContext: {}", contextAuthentication.getName());
        } else {
            log.warn("No authentication found in SecurityContext");
        }
        ResLoginDTO res = new ResLoginDTO();
        User user = this.userService.getUserByEmail(reqLoginDTO.getUsername());
        if (user == null) {
            throw new EmailException("Tài khoản không tồn tại!");
        }
        List<ResLoginDTO.RoleUser> roleUsers = user.getRoles().stream().map(role -> {
            ResLoginDTO.RoleUser roleUser = new ResLoginDTO.RoleUser();
            roleUser.setName(role.getName());
            return roleUser;
        }).collect(Collectors.toList());
        ResLoginDTO.LoginUser loginUser = new ResLoginDTO.LoginUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roleUsers);
        String accessToken = this.securityUtil.createAccessToken(authentication.getName());
        res.setAccessToken(accessToken);
        res.setUser(loginUser);
        String refreshToken = this.securityUtil.createRefreshToken(authentication.getName());
        this.userService.updateRefreshToken(authentication.getName(), refreshToken, StatusEnum.ACTIVE);
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .maxAge(jwtRefreshExpiration)
                .path("/")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(res);
    }

    @PostMapping("/auth/logout")
    @ApiMessage(message = "Đăng xuất")
    @SuppressWarnings("null")
    public ResponseEntity<Void> postLogout() throws EmailException {
        User user = this.userService.getUserInSecurityContext();
        String username = user.getUsername();
        this.userService.updateRefreshToken(username, null, StatusEnum.INACTIVE);
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", null)
                .httpOnly(true)
                .maxAge(0)
                .path("/")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(null);
    }
}
