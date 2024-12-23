package t221124nqt.ecommerce.hair_shop.mapper.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.domain.response.role.ResCreateRoleDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.role.ResGetRoleDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.role.ResRoleDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.role.ResUpdateRoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "displayName", target = "displayName")
    @Mapping(source = "guardName", target = "guardName")
    ResRoleDTO toRoleDTO(Role role);

    @Mapping(source = "name", target = "name")
    ResRoleDTO.PermissionRole toPermissionDTO(Permission permission);

    List<ResRoleDTO.PermissionRole> toPermissionRoleList(List<Permission> permissions);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    ResCreateRoleDTO toCreateRoleDTO(Role role);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResUpdateRoleDTO toUpdateRoleDTO(Role role);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResGetRoleDTO toGetRoleDTO(Role role);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "displayName", target = "displayName")
    @Mapping(source = "guardName", target = "guardName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    Role toRole(Role role);
}
