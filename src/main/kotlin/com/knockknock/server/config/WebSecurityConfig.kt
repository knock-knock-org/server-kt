package com.knockknock.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.util.AntPathMatcher

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .headers()  // H2 console에서 이미지를 불러올 수 없는 에러 발생을 막아주는 코드.
                    .frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests()
                    .requestMatchers("/**").permitAll()
                .and()
                .csrf()
                    .disable()

        return http.build()
    }
}