package t221124nqt.ecommerce.hair_shop.service.imp.auth;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;
import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.order.Customer;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.ReqRegisterDTO;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.request.auth.user.ReqUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.mapper.auth.UserMapper;
import t221124nqt.ecommerce.hair_shop.repository.auth.PermissionRepository;
import t221124nqt.ecommerce.hair_shop.repository.auth.RoleRepository;
import t221124nqt.ecommerce.hair_shop.repository.auth.UserRepository;
import t221124nqt.ecommerce.hair_shop.service.auth.UserService;
import t221124nqt.ecommerce.hair_shop.service.imp.file.IUploadFileService;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final IUploadFileService uploadFileService;

    @Override
    public User createUser(ReqCreateUserDTO user) {
        User newUser = this.userMapper.toUser(user);
        return this.userRepository.save(newUser);
    }

    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        List<Role> roles = user.getRoles().stream().map(role -> this.roleRepository.findById(role.getId()).get())
                .collect(Collectors.toList());
        List<ResCreateUserDTO.RoleUser> roleName = this.userMapper.toRoleUserList(roles);
        List<Permission> permissions = user.getPermissions().stream()
                .map(permission -> this.permissionRepository.findById(permission.getId()).get())
                .collect(Collectors.toList());
        List<ResCreateUserDTO.PermissionUser> permissionName = this.userMapper
                .toPermissionUserList(permissions);
        ResCreateUserDTO resCreateUserDTO = this.userMapper.toCreateUserDTO(user);
        resCreateUserDTO.setRoles(roleName);
        resCreateUserDTO.setPermissions(permissionName);
        return resCreateUserDTO;
    }

    @Override
    public User updateUser(ReqUpdateUserDTO user) {
        User currentUser = this.getUserById(user.getId());
        if (currentUser != null) {
            String password = currentUser.getPassword();
            String rememberToken = currentUser.getRememberToken();
            Timestamp createdAt = currentUser.getCreatedAt();
            String createdBy = currentUser.getCreatedBy();
            currentUser = this.userMapper.toUser(user);
            currentUser.setPassword(password);
            currentUser.setRememberToken(rememberToken);
            currentUser.setCreatedAt(createdAt);
            currentUser.setCreatedBy(createdBy);
            return this.userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public ResUpdateUserDTO convertToResUpdateUserDTO(User currentUser) {
        List<Role> roles = currentUser.getRoles().stream().map(role -> this.roleRepository.findById(role.getId()).get())
                .collect(Collectors.toList());
        List<ResUpdateUserDTO.RoleUser> roleName = this.userMapper.toRoleUserList(roles);
        List<Permission> permissions = currentUser.getPermissions().stream()
                .map(permission -> this.permissionRepository.findById(permission.getId()).get())
                .collect(Collectors.toList());
        List<ResUpdateUserDTO.PermissionUser> permissionName = this.userMapper
                .toPermissionUserList(permissions);
        ResUpdateUserDTO resUpdateUserDTO = this.userMapper.toUpdateUserDTO(currentUser);
        resUpdateUserDTO.setRoles(roleName);
        resUpdateUserDTO.setPermissions(permissionName);
        return resUpdateUserDTO;
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

    @Override
    public void saveUsersToDatabase(MultipartFile file) {
        if (uploadFileService.isValidExcelFile(file)) {
            try {
                List<User> users = uploadFileService.getUsersDataFromExcel(file.getInputStream());
                this.userRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
