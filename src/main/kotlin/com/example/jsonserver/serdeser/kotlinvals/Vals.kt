package com.example.jsonserver.serdeser.kotlinvals

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class Base(
    val title: String,
    val description: String,
)

data class Holder(
    val details: String,
    @field:JsonUnwrapped
    val base: Base,
)