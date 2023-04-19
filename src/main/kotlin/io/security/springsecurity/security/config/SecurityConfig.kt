package io.security.springsecurity.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val allUserDetails = listOf(
            User.builder()
                .username("user")
                .password("{noop}1111")
                .roles("USER")
                .build(),
            User.builder()
                .username("admin")
                .password("{noop}1111")
                .roles("ADMIN")
                .build(),
            User.builder()
                .username("sys")
                .password("{noop}1111")
                .roles("SYS")
                .build()
        )
        return InMemoryUserDetailsManager(allUserDetails)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin()

        // 12) 예외 처리 및 요청 캐시 필터
//            .antMatchers("/login").permitAll()
//            .antMatchers("/user").hasRole("USER")
//            .antMatchers("/admin/pay").hasRole("ADMIN")
//            .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .successHandler { request, response, _ ->
//                val httpSessionRequestCache = HttpSessionRequestCache()
//                val savedRequest = httpSessionRequestCache.getRequest(request, response)
//                response.sendRedirect(savedRequest.redirectUrl)
//            }
//            .and()
//            .exceptionHandling()
//            .authenticationEntryPoint { _, response, _ ->
//                response.sendRedirect("/login")
//            }
//            .accessDeniedHandler { _, response, _ ->
//                response.sendRedirect("/denied")
//            }

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

        // 6) remember me
//            .and()
//            .rememberMe()
//            .rememberMeParameter("remember")
//            .tokenValiditySeconds(3600)
//            .userDetailsService(userDetailsService)

        // 9) 동시 세션 제어
//            .and()
//            .sessionManagement()
//            .maximumSessions(1)
//            .maxSessionsPreventsLogin(true)

        // 9) 세션 고정 보호
//            .and()
//            .sessionManagement()
//            .sessionFixation().changeSessionId()
        return http.build()
    }
}
