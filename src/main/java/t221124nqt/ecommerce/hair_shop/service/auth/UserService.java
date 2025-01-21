package t221124nqt.ecommerce.hair_shop.service.auth;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

public interface UserService {
    User createUser(ReqCreateUserDTO user);

    ResCreateUserDTO convertToResCreateUserDTO(User user);

    User updateUser(ReqUpdateUserDTO user);

    ResUpdateUserDTO convertToResUpdateUserDTO(User user);

    User getUserById(long id);

    ResGetUserDTO convertToResGetUserDTO(User user);

    ResPaginationDTO getAllUsers(Specification<User> spec, Pageable pageable);

    User getUserByEmail(String email);

    void deleteUser(long id);

    boolean checkExistEmail(String email);

    boolean checkExistId(long id);

    User updateRefreshToken(String username, String refreshToken, StatusEnum status) throws EmailException;

    User getUserInSecurityContext() throws EmailException;

    void saveUsersToDatabase(MultipartFile file);
}
