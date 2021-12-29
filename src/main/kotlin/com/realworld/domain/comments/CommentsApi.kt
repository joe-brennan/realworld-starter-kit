package com.realworld.domain.comments

interface CommentsApi {
    fun getCommentsForArticle()
    fun addCommentToArticle()
    fun deleteCommentForArticle()
}