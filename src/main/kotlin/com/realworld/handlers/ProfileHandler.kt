package com.realworld.handlers

import com.realworld.api.ProfileApi
import com.realworld.domain.profile.Profile
import com.realworld.domain.profile.ProfileRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ProfileHandler(val repository: ProfileRepository) : ProfileApi {

    @GetMapping("/profile/{username}")
    override fun getProfile(@PathVariable(value = "username") username: String?): Profile =
        username?.let { repository.findUserByUsername(it) } ?: throw IllegalArgumentException()

    @PostMapping("profiles/{username}/follow")
    override fun followUser(@PathVariable(value = "username") username: String?): Profile {
        TODO("Not yet implemented")
    }

    override fun unfollowUser(username: String?): Profile {
        TODO("Not yet implemented")
    }

    private fun followingUser(): Boolean {
        TODO("Not yet implemented")
    }
}