package com.realworld.api

import com.realworld.domain.user.User
import com.realworld.requests.GetUser
import com.realworld.requests.Login
import com.realworld.requests.Register
import com.realworld.requests.UpdateUser

interface UserApi {
    fun register(request: Register): User
    fun login(request: Login): User
    fun getUser(request: GetUser): User
    fun updateUser(request: UpdateUser): User
}