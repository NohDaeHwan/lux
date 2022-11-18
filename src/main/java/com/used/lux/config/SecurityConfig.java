package com.used.lux.config;

import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.useraccount.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers(
                                "/mypage/**", "/admin/**", "/product/**/order",
                                "/appraisal/new", "/auction/detail/**"
                        ).authenticated()
                        .antMatchers("/admin/appraise/**/new/loading")
                        .permitAll()
                        .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin")
                .failureHandler(customFailureHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/admin")
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository
                .findByUserEmail(username)
                .map(UserAccountDto::from)
                .map(Principal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
