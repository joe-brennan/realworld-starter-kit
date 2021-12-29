package com.realworld.domain.exception

class UserNotFoundException(userId : String) : IllegalStateException("Cannot find account for email: $userId") {
}