package com.example.jsonserver.api.kotlinvars

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class Base(
    val title: String,
    val description: String,
)

data class Holder(
    var details: String,
) {
    @JsonUnwrapped
    var base: Base? = null
}
//TODO: move base out of the constructor