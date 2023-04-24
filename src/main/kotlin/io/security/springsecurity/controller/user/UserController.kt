package io.security.springsecurity.controller.user

import io.security.springsecurity.domain.AccountDto
import io.security.springsecurity.domain.service.UserService
import io.security.springsecurity.domain.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/mypage")
    fun mypage(): String {
        return "user/mypage"
    }

    @PostMapping("/users")
    fun createUser(@RequestBody accountDto: AccountDto) {
        return userService.createUser(accountDto.toEntity())
    }
}
