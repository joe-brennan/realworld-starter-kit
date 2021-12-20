package com.realworld.util

import com.realworld.domain.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtUtil {

    private val jwtTokenValidity: Long = 5 * 60 * 60

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: (Claims) -> T): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String?): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: User): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, user.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(token: String?, user: UserDetails): Boolean? {
        val username = getUsernameFromToken(token)
        return username == user.username && !isTokenExpired(token)
    }
}