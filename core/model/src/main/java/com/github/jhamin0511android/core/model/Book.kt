package com.github.jhamin0511android.core.model

data class Book(
    val isbn: String,
    val thumbnail: String,
    val title: String,
    val author: String,
    val translator: String,
    val price: String,
    val publisher: String,
    val publishDate: String,
    val description: String,
    val link: String,
)
