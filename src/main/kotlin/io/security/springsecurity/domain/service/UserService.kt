package io.security.springsecurity.domain.service

import io.security.springsecurity.domain.Account

interface UserService {
    fun createUser(account: Account)
}
