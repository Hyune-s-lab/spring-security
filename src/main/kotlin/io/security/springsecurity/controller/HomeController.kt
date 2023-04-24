package io.security.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping
    fun home(): String {
        return "home"
    }

    @GetMapping("/loginPage")
    fun login(): String {
        return "login"
    }
}
