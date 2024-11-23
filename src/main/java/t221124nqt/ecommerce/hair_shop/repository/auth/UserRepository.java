package t221124nqt.ecommerce.hair_shop.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
