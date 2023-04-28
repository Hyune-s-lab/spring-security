package io.security.springsecurity.security.service

import io.security.springsecurity.domain.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)?.let {
            AccountContext(it, listOf(SimpleGrantedAuthority(it.role)))
        } ?: throw UsernameNotFoundException("UsernameNotFoundException")
    }
}
