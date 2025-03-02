package t221124nqt.ecommerce.hair_shop.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.component.TranslatorComponent;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.exception.EmailException;
import t221124nqt.ecommerce.hair_shop.exception.IdInvalidException;
import t221124nqt.ecommerce.hair_shop.service.auth.IUserService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Controller")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ApiMessage(message = "Tạo mới người dùng")
    public ResponseEntity<ResCreateUserDTO> createUser(@RequestBody ReqCreateUserDTO user)
            throws EmailException {
        if (this.userService.checkExistEmail(user.getEmail()) == true) {
            throw new EmailException("Email đã tồn tại!");
        }
        User newUser = this.userService.createUser(user);
        ResCreateUserDTO resCreateUserDTO = this.userService.convertToResCreateUserDTO(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resCreateUserDTO);
    }

    @GetMapping("/users")
    @ApiMessage(message = "Tìm kiếm người dùng")
    public ResponseEntity<ResPaginationDTO> getUserById(@Filter Specification<User> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = this.userService.getAllUsers(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @GetMapping("/users/{id}")
    @ApiMessage(message = "Tìm kiếm một người dùng")
    public ResponseEntity<ResGetUserDTO> getUserById(@PathVariable("id") long id) throws IdInvalidException {
        if (this.userService.checkExistId(id) == false) {
            throw new IdInvalidException("Không tồn tại User có id: " + id);
        }
        User user = this.userService.getUserById(id);
        ResGetUserDTO resGetUserDTO = this.userService.convertToResGetUserDTO(user);
        return ResponseEntity.ok().body(resGetUserDTO);
    }

    @PutMapping("/users")
    @ApiMessage(message = "Cập nhật thông tin người dùng")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@Valid @RequestBody ReqUpdateUserDTO user)
            throws IdInvalidException, EmailException {
        if (this.userService.checkExistId(user.getId()) == false) {
            throw new IdInvalidException("Không tồn tại User có id: " + user.getId());
        }
        if (this.userService.checkExistEmail(user.getEmail()) == true) {
            throw new EmailException("Email đã tồn tại!");
        }
        User currentUser = this.userService.updateUser(user);
        ResUpdateUserDTO resUpdateUserDTO = this.userService.convertToResUpdateUserDTO(currentUser);
        return ResponseEntity.ok().body(resUpdateUserDTO);
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage(message = "Xóa người dùng")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if (this.userService.checkExistId(id) == false) {
            throw new IdInvalidException("Không tồn tại User có id: " + id);
        }
        this.userService.deleteUser(id);
        return ResponseEntity.ok().body(null);
    }
}
