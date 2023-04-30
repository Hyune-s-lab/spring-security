package io.security.springsecurity.security.provider

import io.security.springsecurity.security.service.AccountContext
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
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

        if (passwordEncoder.matches(password, accountContext.account.password)) {
            return UsernamePasswordAuthenticationToken(accountContext.account, null, accountContext.authorities)
        }

        println("### CustomAuthenticationProvider::authenticate=BadCredentialsException")
        throw BadCredentialsException("BadCredentialsException")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
