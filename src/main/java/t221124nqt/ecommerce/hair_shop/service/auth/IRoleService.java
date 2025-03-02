package t221124nqt.ecommerce.hair_shop.service.auth;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import t221124nqt.ecommerce.hair_shop.domain.auth.Role;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResCreateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResGetRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.auth.role.ResUpdateRoleDTO;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResPaginationDTO;
import t221124nqt.ecommerce.hair_shop.exception.IdInvalidException;
import t221124nqt.ecommerce.hair_shop.mapper.auth.RoleMapper;
import t221124nqt.ecommerce.hair_shop.repository.auth.RoleRepository;

@Service
public class IRoleService implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public IRoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role createRole(Role role) throws IdInvalidException {
        if (this.checkExistName(role.getName()) == true) {
            throw new IdInvalidException("Đã tồn tại vai trò: " + role.getName());
        }
        role.setName(role.getName().toUpperCase());
        return this.roleRepository.save(role);
    }

    @Override
    public ResCreateRoleDTO convertToResCreateRoleDTO(Role role) {
        ResCreateRoleDTO resCreateRoleDTO = this.roleMapper.toCreateRoleDTO(role);
        return resCreateRoleDTO;
    }

    @Override
    public Role getRoleById(long id) throws IdInvalidException {
        Optional<Role> role = this.roleRepository.findById(id);
        if (!role.isPresent()) {
            throw new IdInvalidException("Không tồn tại User có id: " + id);
        }
        return role.get();
    }

    @Override
    public ResGetRoleDTO convertToResGetRoleDTO(Role role) {
        ResGetRoleDTO resGetRoleDTO = this.roleMapper.toGetRoleDTO(role);
        return resGetRoleDTO;
    }

    @Override
    public ResPaginationDTO getAllRoles(Specification<Role> spec, Pageable pageable) {
        ResPaginationDTO res = new ResPaginationDTO();
        Page<Role> rolePage = this.roleRepository.findAll(spec, pageable);
        List<ResGetRoleDTO> resGetRoleDTO = rolePage.getContent()
                .stream()
                .map(role -> this.roleMapper.toGetRoleDTO(role))
                .collect(Collectors.toList());
        ResPaginationDTO.Meta meta = ResPaginationDTO.addMeta(rolePage, pageable);
        res.setData(resGetRoleDTO);
        res.setMeta(meta);
        return res;
    }

    @Override
    public Role updateRole(Role role) throws IdInvalidException {
        Role currentRole = this.getRoleById(role.getId());
        Timestamp createdAt = currentRole.getCreatedAt();
        String createdBy = currentRole.getCreatedBy();
        currentRole = this.roleMapper.toRole(role);
        currentRole.setName(currentRole.getName().toUpperCase());
        currentRole.setCreatedAt(createdAt);
        currentRole.setCreatedBy(createdBy);
        return this.roleRepository.save(currentRole);
    }

    @Override
    public ResUpdateRoleDTO convertToResUpdateRoleDTO(Role role) {
        ResUpdateRoleDTO resUpdateRoleDTO = this.roleMapper.toUpdateRoleDTO(role);
        return resUpdateRoleDTO;
    }

    @Override
    public void deleteRoleById(long id) throws IdInvalidException {
        if (this.checkExistId(id) == false) {
            throw new IdInvalidException("Không tồn tại id: " + id);
        }
        this.roleRepository.deleteById(id);
    }

    @Override
    public boolean checkExistId(long id) {
        return this.roleRepository.existsById(id);
    }

    @Override
    public boolean checkExistName(String name) {
        return this.roleRepository.existsByName(name);
    }
}
