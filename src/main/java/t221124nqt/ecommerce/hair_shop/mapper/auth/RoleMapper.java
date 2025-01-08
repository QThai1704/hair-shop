package t221124nqt.ecommerce.hair_shop.mapper.auth;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResCreateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResGetRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResUpdateRoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    ResRoleDTO toRoleDTO(Role role);

    ResRoleDTO.PermissionRole toPermissionDTO(Permission permission);

    List<ResRoleDTO.PermissionRole> toPermissionRoleList(List<Permission> permissions);

    ResCreateRoleDTO toCreateRoleDTO(Role role);

    ResUpdateRoleDTO toUpdateRoleDTO(Role role);

    ResGetRoleDTO toGetRoleDTO(Role role);

    Role toRole(Role role);
}
