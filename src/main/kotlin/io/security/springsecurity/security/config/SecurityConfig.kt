package io.security.springsecurity.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/loginPage")
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login_proc")
            .successHandler { _, response, authentication ->
                println("### authentication: ${authentication.name}")
                response.sendRedirect("/")
            }
            .failureHandler { _, response, exception ->
                println("### exception: ${exception.message}")
                response.sendRedirect("/login")
            }
        return http.build()
    }
}
