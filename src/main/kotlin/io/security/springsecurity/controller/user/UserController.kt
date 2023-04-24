package io.security.springsecurity.controller.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/mypage")
    fun mypage(): String {
        return "user/mypage"
    }
}
