package com.realworld.domain.article

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ArticleHandler(val articleRepository: ArticleRepository) : ArticleApi {

    @GetMapping("/articles")
    @ResponseBody
    override fun listArticles(
        @RequestParam(required = false) tag: String?,
        @RequestParam(required = false) author: String?,
        @RequestParam(required = false) favorited: String?
    ): String {
        return Gson().toJson(articleRepository.findAll())
    }

    override fun getFeed() {
        TODO("Not yet implemented")
    }

    override fun getArticle() {
        TODO("Not yet implemented")
    }

    override fun createArticle() {
        TODO("Not yet implemented")
    }

    override fun updateArticle() {
        TODO("Not yet implemented")
    }

    override fun deleteArticle() {
        TODO("Not yet implemented")
    }

    override fun favouriteArticle() {
        TODO("Not yet implemented")
    }

    override fun unfavouriteArticle() {
        TODO("Not yet implemented")
    }
}