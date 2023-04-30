package io.security.springsecurity.security

import io.security.springsecurity.security.common.CustomWebAuthenticationDetailsSource
import io.security.springsecurity.security.handler.CustomAccessDeniedHandler
import io.security.springsecurity.security.provider.CustomAuthenticationProvider
import io.security.springsecurity.security.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val customWebAuthenticationDetailsSource: CustomWebAuthenticationDetailsSource,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun customAuthenticationProvider(): CustomAuthenticationProvider {
        return CustomAuthenticationProvider(passwordEncoder(), userDetailsService)
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
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
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            }
            .httpBasic()
            .authenticationDetailsSource(customWebAuthenticationDetailsSource)
            .and()
            .exceptionHandling()
            .accessDeniedHandler(customAccessDeniedHandler)
        return http.build()
    }
}
