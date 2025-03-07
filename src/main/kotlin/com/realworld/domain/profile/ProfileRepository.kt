package com.realworld.domain.profile

import com.realworld.domain.exception.UserNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<Profile, Long>{

    @Throws(UserNotFoundException::class)
    fun findProfileByUsername(username: String) : Profile?
}