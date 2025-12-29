package com.nguyenngochien.project3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class NnhSecurityConfig {

    @Bean
    public SecurityFilterChain nnhFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt tính năng chống tấn công giả mạo (CSRF) để test API dễ dàng
                .csrf(csrf -> csrf.disable())

                // Cho phép tất cả các request đi qua mà không cần đăng nhập
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}