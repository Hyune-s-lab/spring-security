package io.security.springsecurity.security.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler(
    private val requestCache: RequestCache = HttpSessionRequestCache(),
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        defaultTargetUrl = "/"
        val savedRequest = requestCache.getRequest(request, response)
        println("### CustomAuthenticationSuccessHandler::onAuthenticationSuccess::savedRequest=${savedRequest}")

        if (savedRequest != null) {
            redirectStrategy.sendRedirect(request, response, savedRequest.redirectUrl)
        } else {
            redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
        }
    }
}
