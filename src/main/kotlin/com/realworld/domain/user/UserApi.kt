package com.realworld.domain.user

import com.realworld.domain.user.User
import com.realworld.domain.user.requests.Login
import com.realworld.domain.user.requests.Register
import com.realworld.domain.user.requests.UpdateUser

interface UserApi {
    fun register(request: Register): User
    fun login(request: Login): User
    fun getUser(): User
    fun updateUser(request: UpdateUser): User
}