package com.realworld.domain.article

interface ArticleApi {
    fun listArticles(tag : String?, author: String?, favorited: String?) : String
    fun getFeed()
    fun getArticle()
    fun createArticle()
    fun updateArticle()
    fun deleteArticle()
    fun favouriteArticle()
    fun unfavouriteArticle()
}