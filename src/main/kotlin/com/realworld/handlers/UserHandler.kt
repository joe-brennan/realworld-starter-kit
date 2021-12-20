package com.realworld.handlers

import com.realworld.api.UserApi
import com.realworld.domain.user.User
import com.realworld.domain.user.UserRepository
import com.realworld.handlers.exception.UserAlreadyExistsException
import com.realworld.requests.GetUser
import com.realworld.requests.Login
import com.realworld.requests.Register
import com.realworld.requests.UpdateUser
import com.realworld.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
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

        return repository.save(user);
    }

    override fun login(request: Login): User = TODO("Not yet implemented")

    override fun getUser(request: GetUser): User = TODO("Not yet implemented")

    override fun updateUser(request: UpdateUser): User = TODO("Not yet implemented")
}