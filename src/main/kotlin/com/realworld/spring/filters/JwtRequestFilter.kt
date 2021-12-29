package com.realworld.spring.filters

import com.realworld.authentication.CustomUserDetailsService
import com.realworld.util.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
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

        if (SecurityContextHolder.getContext().authentication == null && authorizationHeader != null && authorizationHeader.startsWith("Bearer ").or(authorizationHeader.startsWith("Token "))) {
            val token = authorizationHeader.substringAfter(' ')
            val username: String = jwtUtil.getUsernameFromToken(token)

            val userDetails = userDetailsService.loadUserByUsername(username)
            if (jwtUtil.validateToken(token, userDetails) == true) {
                request.setAttribute("currentUser", userDetails)
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }

        filterChain.doFilter(request, response)
    }
}