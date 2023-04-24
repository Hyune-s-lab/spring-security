package io.security.springsecurity.domain

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class AccountDto(
    val username: String,
    val password: String,
    val email: String,
    val age: Int,
    val role: String
)

fun AccountDto.toEntity(): Account {
    return Account(
        username = username,
        password = BCryptPasswordEncoder().encode(password),
        email = email,
        age = age,
        role = role
    )
}
