package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.request.ReqLoginDTO;
import t221124nqt.ecommerce.hair_shop.domain.request.ReqRegisterDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.auth.ResLoginDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IUserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Value("${hair-shop.jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshExpiration;

    private final IUserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    public AuthController(
            IUserService userService,
            AuthenticationManagerBuilder authenticationManagerBuilder,
            PasswordEncoder passwordEncoder,
            SecurityUtil securityUtil) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
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
        this.userService.updateRefreshToken(authentication.getName(), refreshToken);
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
    public String postLogout() {
        return "Hello";
    }
}
