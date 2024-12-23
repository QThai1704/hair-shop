package t221124nqt.ecommerce.hair_shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import t221124nqt.ecommerce.hair_shop.domain.auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
    JpaSpecificationExecutor<Role> {
    // Role findByUser(String username);
    Page<Role> findAll(Specification<Role> spec, Pageable pageable);
    boolean existsById(long id);
}
