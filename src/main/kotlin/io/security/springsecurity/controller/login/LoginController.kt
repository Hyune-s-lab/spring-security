package io.security.springsecurity.controller.login

import io.security.springsecurity.domain.Account
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {
    @GetMapping("/denied")
    fun accessDenied(@RequestParam exception: String, model: Model): String {
        println("### LoginController::accessDenied::exception=${exception}")
        val account = SecurityContextHolder.getContext().authentication.principal as Account
        model.addAttribute("username", account.username)
        model.addAttribute("exception", exception)
        return "user/login/denied"
    }
}
