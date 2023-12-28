package com.example.jsonserver.serdeser.nested

data class Base(
    val title: String,
    val description: String,
    val name: Nameable,
)

data class Nameable(
    val name: String,
    val type: String?,
)

data class Holder(
    var details: String,
) {
    //@JsonUnwrapped
    var base: Base? = null
}
