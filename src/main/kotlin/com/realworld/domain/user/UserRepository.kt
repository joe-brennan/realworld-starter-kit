package com.realworld.domain.user

import com.realworld.domain.exception.UserNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{

    @Throws(UserNotFoundException::class)
    fun findUserByEmail(email: String) : User?
}