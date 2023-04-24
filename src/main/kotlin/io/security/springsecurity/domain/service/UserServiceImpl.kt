package io.security.springsecurity.domain.service

import io.security.springsecurity.domain.Account
import io.security.springsecurity.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    @Transactional
    override fun createUser(account: Account) {
        userRepository.save(account)
    }
}
