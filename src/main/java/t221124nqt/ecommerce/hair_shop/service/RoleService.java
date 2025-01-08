package t221124nqt.ecommerce.hair_shop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResCreateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResGetRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResUpdateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

public interface RoleService {
    Role createRole(Role role) throws IdInvalidException;
    ResCreateRoleDTO convertToResCreateRoleDTO(Role role);
    Role getRoleById(long id) throws IdInvalidException;
    ResGetRoleDTO convertToResGetRoleDTO(Role role);
    ResPaginationDTO getAllRoles(Specification<Role> spec, Pageable pageable);
    Role updateRole(Role role) throws IdInvalidException;
    ResUpdateRoleDTO convertToResUpdateRoleDTO(Role role);
    void deleteRoleById(long id) throws IdInvalidException;
    boolean checkExistId(long id);
    boolean checkExistName(String name) throws IdInvalidException;
}
