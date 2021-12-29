package com.realworld.domain.profile

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.realworld.domain.user.User
import org.hibernate.Hibernate
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

    @OneToOne(mappedBy = "address")
    var user: User? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Profile

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , bio = $bio , image = $image )"
    }
}