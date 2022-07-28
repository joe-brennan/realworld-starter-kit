package com.realworld.domain.profile

import com.realworld.domain.user.SpringUser
import com.realworld.domain.user.User
import com.realworld.domain.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ProfileHandler(val repository: ProfileRepository, val userRepository: UserRepository) : ProfileApi {

    @GetMapping("/profiles/{username}")
    override fun getProfile(@PathVariable(value = "username") username: String?): Profile {
        val currentUser = getLoggedInUser()
        val profile = getProfileFromUsername(username)
        return profile.also { it.following = checkIfLoggedInUserFollowsProfile(currentUser!!, profile) }
    }

    @PostMapping("profiles/{username}/follow")
    override fun followUser(@PathVariable(value = "username") username: String?): Profile {
        val currentUser = getLoggedInUser()
        val userToFollow = getProfileFromUsername(username)
        userToFollow.following = true
        currentUser!!.following.add(userToFollow)
        userRepository.save(currentUser)
        return userToFollow
    }

    @DeleteMapping("profiles/{username}/follow")
    override fun unfollowUser(@PathVariable(value = "username") username: String?): Profile {
        val currentUser = getLoggedInUser()
        val userToUnfollow = getProfileFromUsername(username)
        userToUnfollow.following = false
        currentUser!!.following.remove(userToUnfollow)
        userRepository.save(currentUser)
        return userToUnfollow
    }

    private fun checkIfLoggedInUserFollowsProfile(currentUser: User, userToFollow: Profile) =
        currentUser.following.contains(userToFollow)

    private fun getLoggedInUser(): User? {
        val context = SecurityContextHolder.getContext().authentication.principal as SpringUser
        return userRepository.findUserByEmail(context.username)
    }

    private fun getProfileFromUsername(username: String?): Profile =
        username?.let { repository.findProfileByUsername(it) } ?: throw IllegalArgumentException("User does not exist")
}