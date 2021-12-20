package com.realworld.authentication.filters

import com.realworld.authentication.CustomUserDetailsService
import com.realworld.util.JwtUtil
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(private val userDetailsService: CustomUserDetailsService,
                       private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ").or(authorizationHeader.startsWith("Token "))) {
            val token = authorizationHeader.substringAfter(' ')
            val username: String = jwtUtil.getUsernameFromToken(token)

            val userDetails = userDetailsService.loadUserByUsername(username)
            if (jwtUtil.validateToken(token, userDetails) == true) {
                request.setAttribute("currentUser", userDetails)
            }
        }

        filterChain.doFilter(request, response)
    }
}