package t221124nqt.ecommerce.hair_shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    @SuppressWarnings("null")
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsById(long id);
}
