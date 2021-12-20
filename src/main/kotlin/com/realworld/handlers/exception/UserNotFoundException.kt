package com.realworld.handlers.exception

class UserNotFoundException(userId : String) : IllegalStateException("Cannot find account for email: $userId") {
}