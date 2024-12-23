package t221124nqt.ecommerce.hair_shop.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResPermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResUpdatePermissionDTO;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper( PermissionMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "displayName", target = "displayName")
    @Mapping(source = "guardName", target = "guardName")
    ResPermissionDTO toPermissionDTO(Permission permission);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    ResCreatePermissionDTO toCreatePermissionDTO(Permission permission);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResUpdatePermissionDTO toUpdatePermissionDTO(Permission permission);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    ResGetPermissionDTO toGetPermissionDTO(Permission permission);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "displayName", target = "displayName")
    @Mapping(source = "guardName", target = "guardName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "updatedBy", target = "updatedBy")
    Permission toPermission(Permission permission);
}
