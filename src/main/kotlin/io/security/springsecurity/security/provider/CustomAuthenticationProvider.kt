package io.security.springsecurity.security.provider

import io.security.springsecurity.security.common.CustomWebAuthenticationDetails
import io.security.springsecurity.security.service.AccountContext
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class CustomAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val userDetailsService: UserDetailsService,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        println("### CustomAuthenticationProvider::authenticate=${authentication}")

        val username = authentication.name
        val password = authentication.credentials.toString()
        val accountContext = userDetailsService.loadUserByUsername(username) as AccountContext
        if (!passwordEncoder.matches(password, accountContext.account.password)) {
            println("### CustomAuthenticationProvider::authenticate::BadCredentialsException")
            throw BadCredentialsException("BadCredentialsException")
        }

        val secretKey: String? = (authentication.details as CustomWebAuthenticationDetails).secretKey
        if (secretKey == null || secretKey != "secret") {
            println("### CustomAuthenticationProvider::authenticate::InsufficientAuthenticationException")
            throw InsufficientAuthenticationException("Invalid Secret")
        }

        return UsernamePasswordAuthenticationToken(accountContext.account, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
