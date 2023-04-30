package io.security.springsecurity.security.common

import org.springframework.security.web.authentication.WebAuthenticationDetails
import javax.servlet.http.HttpServletRequest

class CustomWebAuthenticationDetails(
    request: HttpServletRequest,
    val secretKey: String? = request.getParameter("secret_key")
) : WebAuthenticationDetails(request)
