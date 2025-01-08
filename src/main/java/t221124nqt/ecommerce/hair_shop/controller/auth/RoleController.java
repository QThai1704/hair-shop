package t221124nqt.ecommerce.hair_shop.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResCreateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResGetRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResUpdateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IRoleService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    private IRoleService roleService;

    public RoleController(IRoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    @ApiMessage(message = "Tạo vai trò mới")
    public ResponseEntity<ResCreateRoleDTO> createRole(@RequestBody Role role) throws IdInvalidException {
        Role newRole = this.roleService.createRole(role);
        ResCreateRoleDTO resCreateRoleDTO = this.roleService.convertToResCreateRoleDTO(newRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateRoleDTO);
    }

    @GetMapping("/roles/{id}")
    @ApiMessage(message = "Tìm kiếm vai trò")
    public ResponseEntity<ResGetRoleDTO> getRoleById(@PathVariable("id") long id) throws IdInvalidException {
        if(this.roleService.checkExistId(id) == false){
            throw new IdInvalidException("Không tồn tại id: " + id);
        }
        Role findRoleById = this.roleService.getRoleById(id);
        ResGetRoleDTO resGetRoleDTO = this.roleService.convertToResGetRoleDTO(findRoleById);
        return ResponseEntity.ok().body(resGetRoleDTO);
    }

    @GetMapping("/roles")
    @ApiMessage(message = "Lấy tất cả danh sách của vai trò")
    public ResponseEntity<ResPaginationDTO> getAllRole(@Filter Specification<Role> spec, Pageable pageable) {
        return ResponseEntity.ok().body(this.roleService.getAllRoles(spec, pageable));
    }

    @PutMapping("/roles")
    @ApiMessage(message = "Cập nhật vai trò")
    public ResponseEntity<ResUpdateRoleDTO> putMethodName(@RequestBody Role role) throws IdInvalidException {
        Role currentRole = this.roleService.updateRole(role);
        ResUpdateRoleDTO resUpdateRoleDTO = this.roleService.convertToResUpdateRoleDTO(currentRole);
        return ResponseEntity.ok().body(resUpdateRoleDTO);
    }

    @DeleteMapping("/roles/{id}")
    @ApiMessage(message = "Xóa vai trò")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") long id) throws IdInvalidException{
        this.roleService.deleteRoleById(id);
        return ResponseEntity.ok().body(null);
    }
}
