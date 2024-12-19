package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.service.UserService;

import javax.naming.Binding;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ResCreateUserDTO> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        User newUser = this.userService.createUser(user);
        ResCreateUserDTO resCreateUserDTO = this.userService.convertToResCreateUserDTO(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resCreateUserDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<ResPaginationDTO> getUserById(@Filter Specification<User> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = this.userService.getAllUsers(spec, pageable);
        return ResponseEntity.ok().body(resPaginationDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResGetUserDTO> getUserById(@PathVariable("id") long id) {
        User user = this.userService.getUserById(id);
        ResGetUserDTO resGetUserDTO = this.userService.convertToResGetUserDTO(user);
        return ResponseEntity.ok().body(resGetUserDTO);
    }

    @PutMapping("/users")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@Valid @RequestBody User user) {
        User currentUser = this.userService.updateUser(user);
        ResUpdateUserDTO resUpdateUserDTO = this.userService.convertToResUpdateUserDTO(currentUser);
        return ResponseEntity.ok().body(resUpdateUserDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().body(null);
    }
}
