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
import t221124nqt.ecommerce.hair_shop.util.exception.CommonException;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;


import org.springframework.http.HttpStatus;
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
    private final IUserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    public AuthController(
        IUserService userService,
        AuthenticationManagerBuilder authenticationManagerBuilder,
        PasswordEncoder passwordEncoder,
        SecurityUtil securityUtil){
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ResCreateUserDTO> postRegisterUser(@Valid @RequestBody ReqRegisterDTO reqRegisterDTO) throws EmailException, CommonException {
        if(this.userService.checkExistEmail(reqRegisterDTO.getUsername())  == true){
            throw new EmailException("Tài khoản đã tồn tại!");
        }else{
            if(reqRegisterDTO.getPassword().equals(reqRegisterDTO.getComfirmPassword()) == false){
                throw new CommonException("Mật khẩu không khớp");
            }
        }
        ResCreateUserDTO resCreateUserDTO = this.userService.registerUser(reqRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateUserDTO);
    }

    @PostMapping("/auth/login")
    public String postLogin(@Valid @RequestBody ReqLoginDTO reqLoginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(reqLoginDTO.getUsername(), reqLoginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResLoginDTO res = new ResLoginDTO();
        User user = this.userService.getUserByEmail(reqLoginDTO.getUsername());
        if(user != null){
            ResLoginDTO.LoginedUser loginedUser = new ResLoginDTO.LoginedUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole());
        }
        return "Hello";
    }

    @PostMapping("/auth/logout")
    public String postLogout() {
        return "Hello";
    }
}
