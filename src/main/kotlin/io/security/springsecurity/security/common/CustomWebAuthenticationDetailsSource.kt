package io.security.springsecurity.security.common

import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class CustomWebAuthenticationDetailsSource : AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    override fun buildDetails(request: HttpServletRequest): WebAuthenticationDetails {
        println("### CustomWebAuthenticationDetailsSource::buildDetails::request.queryString=${request.queryString}")
        return CustomWebAuthenticationDetails(request)
    }
}
