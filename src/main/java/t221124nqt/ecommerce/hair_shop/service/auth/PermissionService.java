package t221124nqt.ecommerce.hair_shop.service.auth;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResUpdatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;

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
