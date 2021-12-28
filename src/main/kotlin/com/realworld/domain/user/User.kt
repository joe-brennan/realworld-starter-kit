package com.realworld.domain.user

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.realworld.domain.profile.Profile
import org.hibernate.Hibernate
import javax.persistence.*

@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME)
@Entity
@Table(name = "users")
data class User(var username: String,
                var email: String,
                var password: String,
                var token: String = "",
                var bio: String = "",
                var image: String? = null,
                @Id @GeneratedValue var id: Long? = null) {


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var profile: Profile? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , email = $email )"
    }
}

fun User.updateProfile(){
    profile!!.username = username
    profile!!.bio = bio
    profile!!.image = image
}