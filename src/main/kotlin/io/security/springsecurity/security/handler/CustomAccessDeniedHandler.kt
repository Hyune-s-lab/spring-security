package io.security.springsecurity.security.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler(
    private val errorPage: String = "/denied",
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()
) : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val deniedUrl = errorPage + "?exception=" + accessDeniedException.message
        println("### CustomAccessDeniedHandler::handle::deniedUrl=${deniedUrl}")

        redirectStrategy.sendRedirect(request, response, deniedUrl)
    }
}
