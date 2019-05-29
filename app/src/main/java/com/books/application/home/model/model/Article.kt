package com.books.application.home.model.model

data class Articles(
    var list: MutableList<Article>?,
    var index: String?,
    var has_more: Int
)

data class Article(
    var article_id: String?,
    var category: String?,
    var title: String?,
    var publish_time: String?,
    var favor_count: String?,
    var image: String?,
    var url: String?
)
