package com.realworld.handlers

import com.realworld.api.UserApi
import com.realworld.domain.profile.Profile
import com.realworld.domain.user.User
import com.realworld.domain.user.UserRepository
import com.realworld.domain.user.updateProfile
import com.realworld.handlers.exception.UserAlreadyExistsException
import com.realworld.requests.Login
import com.realworld.requests.Register
import com.realworld.requests.UpdateUser
import com.realworld.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserHandler(val repository: UserRepository,
                  val passwordEncoder: PasswordEncoder,
                  val jwtUtil: JwtUtil,
                  val auth: AuthenticationManager,
                  val userDetailsService: UserDetailsService) : UserApi {

    @PostMapping("/users")
    @ResponseBody
    override fun register(@RequestBody request: Register): User {

        if (repository.findUserByEmail(request.email!!) != null) throw UserAlreadyExistsException(request.email!!)

        val user = User(request.username!!, request.email!!, passwordEncoder.encode(request.password!!))
        user.token = jwtUtil.generateToken(user)

        user.profile = Profile(user.username)
        return repository.save(user);
    }

    @PostMapping("/users/login")
    @ResponseBody
    override fun login(@RequestBody request: Login): User {
        val authentication = auth.authenticate(UsernamePasswordAuthenticationToken(request.email!!, request.password!!))
        SecurityContextHolder.getContext().authentication = authentication

        val principal = authentication.principal as SpringUser
        val userDetails = userDetailsService.loadUserByUsername(principal.username)
        val token = jwtUtil.generateTokenForExistingUser(userDetails)
        val user = getUserByEmail(request.email!!)
        user.token = token

        return repository.save(user)
    }

    @GetMapping("/user")
    override fun getUser(): User = getUserFromContext()

    @PutMapping("/user")
    override fun updateUser(@RequestBody request: UpdateUser) : User{
        val userFromContext = getUserFromContext()

        userFromContext.username = request.username ?: userFromContext.username
        userFromContext.password = passwordEncoder.encode(request.password) ?: userFromContext.password
        userFromContext.bio = request.bio ?: userFromContext.bio
        userFromContext.image = request.image ?: userFromContext.image
        userFromContext.updateProfile()

        return repository.save(userFromContext)
    }

    private fun getUserFromContext(): User {
        val context = SecurityContextHolder.getContext().authentication.principal as SpringUser
        return getUserByEmail(context.username)
    }

    private fun getUserByEmail(email : String?) : User =
        repository.findUserByEmail(email!!) ?: throw UserAlreadyExistsException(email)
}

typealias SpringUser = org.springframework.security.core.userdetails.User