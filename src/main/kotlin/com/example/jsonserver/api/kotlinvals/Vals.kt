package com.example.jsonserver.api.kotlinvals

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
//TODO: move base out of the constructor