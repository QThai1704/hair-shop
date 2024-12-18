package t221124nqt.ecommerce.hair_shop.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.mapper.user.UserMapper;
import t221124nqt.ecommerce.hair_shop.repository.UserRepository;
import t221124nqt.ecommerce.hair_shop.service.UserService;


@Service
public class IUserService implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public IUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        ResCreateUserDTO resCreateUserDTO = this.userMapper.toCreateUserDTO(user);
        return resCreateUserDTO;
    }

    @Override
    public User updateUser(User user) {
        User currentUser = this.getUserById(user.getId());
        if(currentUser != null){
            currentUser = this.userMapper.toUser(user);
            currentUser.setPassword(currentUser.getPassword());
            currentUser.setRememberToken(currentUser.getRememberToken());
            currentUser.setCreateAt(currentUser.getCreateAt());
            currentUser.setCreatedBy(currentUser.getCreatedBy());
            return this.userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public ResUpdateUserDTO convertToResUpdateUserDTO(User currentUser) {
        if(currentUser != null){
            currentUser = this.userMapper.toUser(currentUser);
            ResUpdateUserDTO resUpdateUserDTO = this.userMapper.toUpdateUserDTO(currentUser);
            return resUpdateUserDTO;
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> getUser = this.userRepository.findById(id);
        if(getUser.isPresent()){
            return getUser.get();
        }
        return null;
    }

    @Override
    public ResGetUserDTO convertToResGetUserDTO(User user) {
        if(user != null){
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
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

}
