package io.security.springsecurity.controller.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {
    @GetMapping("/messages")
    fun messages(): String {
        return "user/messages"
    }
}
