package t221124nqt.ecommerce.hair_shop.component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import t221124nqt.ecommerce.hair_shop.repository.UserRepository;

@Component("userDetailsCustom")
public class UserDetailsCustom implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<t221124nqt.ecommerce.hair_shop.domain.auth.User> user = this.userRepository.findByEmail(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Tài khoản/ mật khẩu không hợp lệ!");
        }
        t221124nqt.ecommerce.hair_shop.domain.auth.User userByEmail = user.get();
        List<SimpleGrantedAuthority> roles = userByEmail.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new User(
                userByEmail.getEmail(),
                userByEmail.getPassword(),
                roles);
    }

}
