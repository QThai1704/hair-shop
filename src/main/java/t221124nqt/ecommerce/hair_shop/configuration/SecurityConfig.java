package t221124nqt.ecommerce.hair_shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import t221124nqt.ecommerce.hair_shop.component.CustomAuthenticationEntryPoint;

import org.springframework.security.config.Customizer;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http,
                        CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
                http
                                .csrf(c -> c.disable())
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests(
                                                authz -> authz
                                                                .requestMatchers("/**", "/api/v1/auth/**",
                                                                                "/v3/api-docs/**",
                                                                                "/swagger-ui/**",
                                                                                "/swagger-ui.html",
                                                                                "/api/v1/convert-file-csv-to-db")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.GET, "/api/v1/users")
                                                                .permitAll()
                                                                .anyRequest().authenticated())
                                .formLogin(f -> f.disable())
                                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
                                                .authenticationEntryPoint(customAuthenticationEntryPoint))
                                .oauth2Login(oauth2 -> oauth2
                                                .loginPage("/oauth2/authorization/google")
                                                .defaultSuccessUrl("/admin", true));
                return http.build();
        }
}
