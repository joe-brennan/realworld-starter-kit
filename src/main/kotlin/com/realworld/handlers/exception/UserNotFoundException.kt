package com.realworld.handlers.exception

class UserNotFoundException(userId : String) : IllegalStateException("Cannot find user: $userId") {
}