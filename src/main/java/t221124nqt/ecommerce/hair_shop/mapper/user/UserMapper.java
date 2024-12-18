package t221124nqt.ecommerce.hair_shop.mapper.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResCreateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResGetUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUpdateUserDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.user.ResUserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    @Mapping(source = "username", target = "username")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "jobTitle", target = "jobTitle")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "activeCode", target = "activeCode")
    @Mapping(source = "status", target = "status")
    ResUserDTO toUserDTO(User user);

    @Mapping(source = "name", target = "name")
    ResUserDTO.RoleUser toRoleDTO(Role role);

    @Mapping(source = "name", target = "name")
    ResUserDTO.PermissionUser toRoleDTO(Permission permission);

    List<ResUserDTO.RoleUser> toRoleUserList(List<Role> roles);
    List<ResUserDTO.PermissionUser> toPermissionUserList(List<Permission> permissions);

    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "createdBy", target = "createdBy")
    ResCreateUserDTO toCreateUserDTO(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "updateAt", target = "updateAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResUpdateUserDTO toUpdateUserDTO(User user);

    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updateAt", target = "updateAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResGetUserDTO toGetUserDTO(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "jobTitle", target = "jobTitle")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "activeCode", target = "activeCode")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updateAt", target = "updateAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    User toUser(User user);
}
