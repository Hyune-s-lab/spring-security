package io.security.springsecurity.domain.repository

import io.security.springsecurity.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long>
