package com.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/store/register").hasRole("MANAGER")
                        .requestMatchers("/store/list", "/store/list/**").permitAll()
                        .requestMatchers("/reservation/**").authenticated() // 로그인된 사용자만 접근 가능
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/review/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("MANAGER")
                .build();

        var user = User.withUsername("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

//        var kiki = User.withUsername("kiki")
//                .password(passwordEncoder().encode("kiki123"))
//                .roles("USER")
//                .build();

        return new InMemoryUserDetailsManager(manager, user);
    }
}
