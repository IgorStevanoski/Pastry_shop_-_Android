package com.example.domaci3.domain

import android.util.Patterns

data class ProductData(
    val id: String,
    val type: String,
    val name: String,
    val description: String,
    val price: Int,
    val composition: String,
    val image: String,
    val comments: List<CommentData>,
)

data class CommentData(
    val username: String,
    val text: String,
)
