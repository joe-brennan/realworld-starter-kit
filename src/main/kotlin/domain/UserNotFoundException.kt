package domain

class UserNotFoundException(userId : String) : IllegalStateException("Cannot find user: $userId") {
}