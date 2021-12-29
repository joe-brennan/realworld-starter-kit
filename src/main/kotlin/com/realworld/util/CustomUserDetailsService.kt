package com.realworld.util

import com.realworld.domain.user.UserRepository
import com.realworld.domain.exception.UserNotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val userDetails: DomainUser = (userRepository.findUserByEmail(email)
                ?: throw UserNotFoundException("User not found"))
        return User(userDetails.email, userDetails.password, emptyList())
    }
}

typealias DomainUser = com.realworld.domain.user.User

