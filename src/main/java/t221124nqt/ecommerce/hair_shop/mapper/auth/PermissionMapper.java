package t221124nqt.ecommerce.hair_shop.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResPermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResUpdatePermissionDTO;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    ResPermissionDTO toPermissionDTO(Permission permission);

    ResCreatePermissionDTO toCreatePermissionDTO(Permission permission);

    ResUpdatePermissionDTO toUpdatePermissionDTO(Permission permission);

    ResGetPermissionDTO toGetPermissionDTO(Permission permission);

    Permission toPermission(Permission permission);
}
