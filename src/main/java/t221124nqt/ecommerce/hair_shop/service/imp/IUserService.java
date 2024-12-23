package t221124nqt.ecommerce.hair_shop.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.request.ReqRegisterDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.mapper.user.UserMapper;
import t221124nqt.ecommerce.hair_shop.repository.UserRepository;
import t221124nqt.ecommerce.hair_shop.service.UserService;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

@Service
@Slf4j
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public IUserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(@Valid User user) {
        ResCreateUserDTO resCreateUserDTO = this.userMapper.toCreateUserDTO(user);
        return resCreateUserDTO;
    }

    @Override
    public User updateUser(User user) {
        User currentUser = this.getUserById(user.getId());
        if (currentUser != null) {
            currentUser = this.userMapper.toUser(user);
            currentUser.setPassword(currentUser.getPassword());
            currentUser.setRememberToken(currentUser.getRememberToken());
            currentUser.setCreatedAt(currentUser.getCreatedAt());
            currentUser.setCreatedBy(currentUser.getCreatedBy());
            return this.userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public ResUpdateUserDTO convertToResUpdateUserDTO(User currentUser) {
        if (currentUser != null) {
            currentUser = this.userMapper.toUser(currentUser);
            ResUpdateUserDTO resUpdateUserDTO = this.userMapper.toUpdateUserDTO(currentUser);
            return resUpdateUserDTO;
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> getUser = this.userRepository.findById(id);
        if (getUser.isPresent()) {
            return getUser.get();
        }
        return null;
    }

    @Override
    public ResGetUserDTO convertToResGetUserDTO(User user) {
        if (user != null) {
            ResGetUserDTO resGetUserDTO = this.userMapper.toGetUserDTO(user);
            return resGetUserDTO;
        }
        return null;
    }

    @Override
    public ResPaginationDTO getAllUsers(Specification<User> spec, Pageable pageable) {
        ResPaginationDTO resPaginationDTO = new ResPaginationDTO();
        Page<User> userPage = this.userRepository.findAll(spec, pageable);
        List<ResGetUserDTO> resGetUserDTOs = userPage.getContent()
                .stream()
                .map(user -> this.userMapper.toGetUserDTO(user))
                .collect(Collectors.toList());
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(userPage, pageable);
        resPaginationDTO.setData(resGetUserDTOs);
        resPaginationDTO.setMeta(meta);
        return resPaginationDTO;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistId(long id) {
        return this.userRepository.existsById(id);
    }

    public ResCreateUserDTO registerUser(ReqRegisterDTO reqRegisterDTO) {
        String password = this.passwordEncoder.encode(reqRegisterDTO.getPassword());
        User newUser = new User();
        newUser.setUsername(reqRegisterDTO.getUsername());
        newUser.setLastName(reqRegisterDTO.getLastName());
        newUser.setFirstName(reqRegisterDTO.getFirstName());
        newUser.setPassword(password);
        newUser.setEmail(reqRegisterDTO.getUsername());
        this.userRepository.save(newUser);
        ResCreateUserDTO resCreateUserDTO = UserMapper.INSTANCE.toCreateUserDTO(newUser);
        return resCreateUserDTO;
    }

    @Override
    public User updateRefreshToken(String username, String refreshToken, StatusEnum status) throws EmailException {
        User user = this.getUserByEmail(username);
        if (user == null) {
            throw new EmailException("Tài khoản không tồn tại!");
        }
        user.setRememberToken(refreshToken);
        user.setStatus(status);
        return this.userRepository.save(user);
    }

    @Override
    public User getUserInSecurityContext() throws EmailException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = extractPrincipal(authentication);
        if (email == null) {
            throw new EmailException("Tài khoản không tồn tại!");
        }
        return this.getUserByEmail(email);
    }

    private String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }
}
