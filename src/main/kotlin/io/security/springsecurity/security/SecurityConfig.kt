package io.security.springsecurity.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val allUserDetails = listOf(
            User.builder()
                .username("user")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build(),
            User.builder()
                .username("manager")
                .password(passwordEncoder().encode("1111"))
                .roles("MANAGER")
                .build(),
            User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1111"))
                .roles("ADMIN")
                .build()
        )
        return InMemoryUserDetailsManager(allUserDetails)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .cors().and()
            .authorizeHttpRequests {
                it
                    .antMatchers("/", "/users").permitAll()
                    .antMatchers("/mypage").hasRole("USER")
                    .antMatchers("/messages").hasRole("MANAGER")
                    .antMatchers("/config").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .httpBasic()
        return http.build()
    }
}
