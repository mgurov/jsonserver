package com.example.jsonserver.api.kotlinvals

data class Base(
    val title: String,
    val description: String,
)

data class Holder(
    val details: String,
    val base: Base,
)