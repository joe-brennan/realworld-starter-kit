package com.realworld.domain.profile

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.realworld.api.ProfileApi
import com.realworld.domain.user.User
import javax.persistence.*


@JsonTypeName("profile")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Entity
@Table(name = "profile")
data class Profile(
    var username: String,
    var bio: String = "",
    var image: String? = null,
    @Id @GeneratedValue var id: Long? = null) {

    @OneToMany(targetEntity=Profile::class, fetch=FetchType.EAGER)
    var following: List<Profile> = emptyList()

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
}