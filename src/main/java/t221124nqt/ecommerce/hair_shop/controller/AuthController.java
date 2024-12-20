package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.domain.request.ReqRegisterDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IUserService;
import t221124nqt.ecommerce.hair_shop.util.exception.CommonException;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService){
        this.userService = userService;
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
    public String postLogin() {
        return "Hello";
    }

    @PostMapping("/auth/logout")
    public String postLogout() {
        return "Hello";
    }
}
