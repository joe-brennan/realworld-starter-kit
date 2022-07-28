package com.realworld.domain.article

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@JsonTypeName("article")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME)
@Entity
@Table(name = "articles")
data class Article(@Id
                   @Column(name = "id", nullable = false)
                   var id: Long? = null,
                   var title : String,
                   var slug : String,
                   var body : String,
                   var createdAt : String,
                   var updatedAt : String,
                   var description : String,
                   var tagList : String,
                   var author : String,
                   var favorited : String,
                   var favoritesCount : String) {

}