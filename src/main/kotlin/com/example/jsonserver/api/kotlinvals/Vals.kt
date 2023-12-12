package com.example.jsonserver.api.kotlinvals

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class Base(
    val title: String,
    val description: String,
)

data class Holder(
    val details: String,
    @JsonUnwrapped
    val base: Base,
)