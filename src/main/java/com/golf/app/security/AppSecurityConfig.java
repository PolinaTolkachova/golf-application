
package com.golf.app.security;

import com.golf.app.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user")
            .password(passwordEncoder().encode("userPass"))
            .roles(Role.USER.name())
            .build();
        UserDetails user2 = User.withUsername("1")
            .password(passwordEncoder().encode("1"))
            .roles(Role.USER.name())
            .build();
        UserDetails coach = User.withUsername("coach")
            .password(passwordEncoder().encode("coachPass"))
            .roles(Role.COACH.name())
            .build();
        UserDetails admin = User.withUsername("a")
            .password(passwordEncoder().encode("a"))
            .roles(Role.ADMIN.name())
            .build();

        return new InMemoryUserDetailsManager(user1, user2, coach, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

