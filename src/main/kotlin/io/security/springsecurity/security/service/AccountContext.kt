package io.security.springsecurity.security.service

import io.security.springsecurity.domain.Account
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class AccountContext(
    private val account: Account,
    authorities: List<SimpleGrantedAuthority>
) : User(account.username, account.password, authorities)
