package com.realworld.domain.exception

class UserAlreadyExistsException(email : String) : IllegalStateException("User already exists for: $email")