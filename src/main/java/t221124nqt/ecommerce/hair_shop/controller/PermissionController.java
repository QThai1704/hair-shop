package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.domain.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.domain.response.permission.ResUpdatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.service.imp.IPermissionService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class PermissionController {
    private final IPermissionService permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permissions")
    @ApiMessage(message = "Tạo quyền")
    public ResponseEntity<ResCreatePermissionDTO> createPermission(@RequestBody Permission permission) {
        Permission newPermission = this.permissionService.createPermission(permission);
        ResCreatePermissionDTO resCreatePermissionDTO = this.permissionService
                .convertToResCreatePermissionDTO(newPermission);
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreatePermissionDTO);
    }

    @GetMapping("/permissions/{id}")
    @ApiMessage(message = "Tìm kiếm quyền")
    public ResponseEntity<ResGetPermissionDTO> getMethodName(@PathVariable("id") long id) {
        Permission findPermissionById = this.permissionService.getPermissionById(id);
        ResGetPermissionDTO resGetPermissionDTO = this.permissionService
                .convertToResGetPermissionDTO(findPermissionById);
        return ResponseEntity.ok().body(resGetPermissionDTO);
    }

    @GetMapping("/permissions")
    @ApiMessage(message = "Danh sách quyền")
    public ResponseEntity<ResPaginationDTO> getMethodName(@Filter Specification<Permission> spec, Pageable pageable) {
        return ResponseEntity.ok().body(this.permissionService.getAllPermissions(spec, pageable));
    }

    @PutMapping("/permissions")
    @ApiMessage(message = "Cập nhật quyền")
    public ResponseEntity<ResUpdatePermissionDTO> updatePermission(@RequestBody Permission permission) {
        Permission updatePermission = this.permissionService.updatePermission(permission);
        ResUpdatePermissionDTO resUpdatePermissionDTO = this.permissionService
                .convertToResUpdatePermissionDTO(updatePermission);
        return ResponseEntity.ok().body(resUpdatePermissionDTO);
    }

    @DeleteMapping("/permissions/{id}")
    @ApiMessage(message = "Xóa quyền")
    public ResponseEntity<Void> deletePermission(@PathVariable("id") long id) {
        this.permissionService.deletePermission(id);
        return ResponseEntity.ok().body(null);
    }
}
