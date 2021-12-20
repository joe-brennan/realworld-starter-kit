package com.realworld.handlers.exception

class UserAlreadyExistsException(email : String) : IllegalStateException("User already exists for: $email")