package t221124nqt.ecommerce.hair_shop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.request.user.ReqCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.request.user.ReqUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ResUserDTO toDTO(User user);

    ResUserDTO.RoleUser toRoleDTO(Role role);

    ResUserDTO.PermissionUser toPermissionDTO(Permission permission);

    List<ResUserDTO.RoleUser> toRoleUserList(List<Role> roles);

    List<ResUserDTO.PermissionUser> toPermissionUserList(List<Permission> permissions);

    ResCreateUserDTO toCreateUserDTO(User user);

    ResUpdateUserDTO toUpdateUserDTO(User user);

    ResGetUserDTO toGetUserDTO(User user);

    User toUser(ReqCreateUserDTO reqCreateUserDTO);

    User toUser(ReqUpdateUserDTO reqUpdateUserDTO);
}
