package io.security.springsecurity.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(val userDetailsService: UserDetailsService) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            // 3) form login 인증
//            .loginPage("/loginPage")
//            .defaultSuccessUrl("/")
//            .failureUrl("/login")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .loginProcessingUrl("/login_proc")
//            .successHandler { _, response, authentication ->
//                println("### authentication: ${authentication.name}")
//                response.sendRedirect("/")
//            }
//            .failureHandler { _, response, exception ->
//                println("### exception: ${exception.message}")
//                response.sendRedirect("/login")
//            }

            // 5) logout
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/login")
//            .addLogoutHandler { request, _, _ ->
//                println("### logout handler")
//                request.session.invalidate()
//            }
//            .logoutSuccessHandler { _, response, _ ->
//                println("### logout success handler")
//                response.sendRedirect("/login")
//            }
//            .deleteCookies("remember-me")
            .and()
            .rememberMe()
            .rememberMeParameter("remember")
            .tokenValiditySeconds(3600)
            .userDetailsService(userDetailsService)
        return http.build()
    }
}
