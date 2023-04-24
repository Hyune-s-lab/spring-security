package io.security.springsecurity.controller.admin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController {
    @GetMapping("/config")
    fun config(): String {
        return "admin/config"
    }
}
