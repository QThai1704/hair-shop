package t221124nqt.ecommerce.hair_shop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

public interface UserService {
    User createUser(User user);

    ResCreateUserDTO convertToResCreateUserDTO(User user);

    User updateUser(User user);

    ResUpdateUserDTO convertToResUpdateUserDTO(User user);

    User getUserById(long id);

    ResGetUserDTO convertToResGetUserDTO(User user);

    ResPaginationDTO getAllUsers(Specification<User> spec, Pageable pageable);

    User getUserByEmail(String email);

    void deleteUser(long id);

    boolean checkExistEmail(String email);

    boolean checkExistId(long id);

    User updateRefreshToken(String username, String refreshToken) throws EmailException;
}
