package ru.est0y.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.est0y.services.UserService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration {

    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer
                        .defaultSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/books").hasAnyRole("CLIENT")
                        .requestMatchers("/creation", "/edit/*").hasAnyRole("EDITOR")
                        .requestMatchers(
                                "/book", "/book/*",
                                "/genre", "/author").hasAnyRole("CLIENT")
                        .anyRequest().denyAll()
                );
        return http.build();
    }

    //SecurityFilterChain с ограничениями на http методы
    @Bean
    public SecurityFilterChain securityFilterChainWithHttpMethodFilter(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer
                        .defaultSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/books").hasAnyRole("CLIENT")
                        .requestMatchers("/creation", "/edit/*").hasAnyRole("EDITOR")
                        .requestMatchers(HttpMethod.POST, "/book").hasAnyRole("EDITOR")
                        .requestMatchers(HttpMethod.PUT, "/book").hasAnyRole("EDITOR")
                        .requestMatchers(HttpMethod.DELETE, "/book/*").hasAnyRole("EDITOR")
                        .requestMatchers(HttpMethod.GET,
                                "/book", "/book/*",
                                "/genre", "/author").hasAnyRole("CLIENT")
                        .anyRequest().denyAll()
                );
        return http.build();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_EDITOR > ROLE_CLIENT");
        return roleHierarchy;
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new UserDetailsServiceImpl(userService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
