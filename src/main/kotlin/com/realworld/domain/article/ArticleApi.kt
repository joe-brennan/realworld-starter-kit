package com.realworld.domain.article

interface ArticleApi {
    fun listArticles()
    fun getFeed()
    fun getArticle()
    fun createArticle()
    fun updateArticle()
    fun deleteArticle()
    fun favouriteArticle()
    fun unfavouriteArticle()
}