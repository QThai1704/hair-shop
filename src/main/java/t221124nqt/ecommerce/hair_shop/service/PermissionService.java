package t221124nqt.ecommerce.hair_shop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResUpdatePermissionDTO;

public interface PermissionService {
    Permission createPermission(Permission permission);

    ResCreatePermissionDTO convertToResCreatePermissionDTO(Permission permission);

    Permission getPermissionById(long id);

    ResGetPermissionDTO convertToResGetPermissionDTO(Permission permission);

    ResPaginationDTO getAllPermissions(Specification<Permission> specification, Pageable pageable);

    Permission updatePermission(Permission permission);

    ResUpdatePermissionDTO convertToResUpdatePermissionDTO(Permission permission);

    void deletePermission(long id);

    boolean checkExistId(long id);

    boolean checkExistName(String name);
}
