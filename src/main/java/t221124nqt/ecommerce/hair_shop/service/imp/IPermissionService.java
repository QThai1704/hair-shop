package t221124nqt.ecommerce.hair_shop.service.imp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.auth.Permission;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResCreatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResGetPermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.permission.ResUpdatePermissionDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.mapper.auth.PermissionMapper;
import t221124nqt.ecommerce.hair_shop.repository.PermissionRepository;
import t221124nqt.ecommerce.hair_shop.service.PermissionService;

@Service
public class IPermissionService implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public IPermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return this.permissionRepository.save(permission);
    }

    @Override
    public ResCreatePermissionDTO convertToResCreatePermissionDTO(Permission permission) {
        ResCreatePermissionDTO resCreatePermissionDTO = this.permissionMapper.toCreatePermissionDTO(permission);
        return resCreatePermissionDTO;
    }

    @Override
    public Permission getPermissionById(long id) {
        Optional<Permission> permissionOptional = this.permissionRepository.findById(id);
        if (permissionOptional.isPresent()) {
            return permissionOptional.get();
        }
        return null;
    }

    @Override
    public ResGetPermissionDTO convertToResGetPermissionDTO(Permission permission) {
        ResGetPermissionDTO resGetPermissionDTO = this.permissionMapper.toGetPermissionDTO(permission);
        return resGetPermissionDTO;
    }

    @Override
    public ResPaginationDTO getAllPermissions(Specification<Permission> specification, Pageable pageable) {
        ResPaginationDTO res = new ResPaginationDTO();
        Page<Permission> permissionPage = this.permissionRepository.findAll(specification, pageable);
        List<ResGetPermissionDTO> resGetPermissionDTOs = permissionPage.getContent().stream()
                .map(permission -> this.permissionMapper.toGetPermissionDTO(permission))
                .collect(Collectors.toList());
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(permissionPage, pageable);
        res.setData(resGetPermissionDTOs);
        res.setMeta(meta);
        return res;
    }

    @Override
    public Permission updatePermission(Permission permission) {
        Permission currentPermission = this.getPermissionById(permission.getId());
        if (currentPermission != null) {
            Timestamp createdAt = currentPermission.getCreatedAt();
            String createdBy = currentPermission.getCreatedBy();
            currentPermission = this.permissionMapper.toPermission(permission);
            currentPermission.setCreatedAt(createdAt);
            currentPermission.setCreatedBy(createdBy);
            return this.permissionRepository.save(currentPermission);
        }
        return null;
    }

    @Override
    public ResUpdatePermissionDTO convertToResUpdatePermissionDTO(Permission permission) {
        ResUpdatePermissionDTO resUpdatePermissionDTO = this.permissionMapper.toUpdatePermissionDTO(permission);
        return resUpdatePermissionDTO;
    }

    @Override
    public void deletePermission(long id) {
        this.permissionRepository.deleteById(id);
    }

    @Override
    public boolean checkExistId(long id) {
        return this.permissionRepository.existsById(id);
    }

    @Override
    public boolean checkExistName(String name) {
        return this.permissionRepository.existsByName(name);
    }

}
