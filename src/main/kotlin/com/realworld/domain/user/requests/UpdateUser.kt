package com.realworld.domain.user.requests

import com.fasterxml.jackson.annotation.JsonRootName
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@JsonRootName("user")
class UpdateUser(
    @Size(min = 1, message = "can't be empty")
    @Pattern(regexp = "^\\w+$", message = "must be alphanumeric")
    var username: String?,
    @Size(min = 1, message = "can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "must be a valid email")
    var email: String?,
    @Size(min = 1, message = "can't be empty")
    var password: String?,
    var bio: String?,
    var image: String?
)