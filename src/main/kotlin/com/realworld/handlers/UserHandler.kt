package com.realworld.handlers

import com.realworld.api.UserApi
import com.realworld.domain.user.UserRepository
import com.realworld.domain.user.User
import com.realworld.requests.GetUser
import com.realworld.requests.Login
import com.realworld.requests.Register
import com.realworld.requests.UpdateUser
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserHandler : UserApi {

    @Autowired
    private lateinit var repository: UserRepository

    @PostMapping("/users")
    @ResponseBody
    override fun register(@RequestBody request: Register): User {
        val user = User(request.username!!, request.email!!, BCrypt.hashpw(request.password, BCrypt.gensalt()))
        return repository.save(user);
    }

    override fun login(request: Login): User = TODO("Not yet implemented")

    override fun getUser(request: GetUser): User = TODO("Not yet implemented")

    override fun updateUser(request: UpdateUser): User = TODO("Not yet implemented")
}