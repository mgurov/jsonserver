package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.Instant

data class MutableProps(
    val name: String,
    val description: String,
) {
    companion object {
        val example = MutableProps(
            name = "example name",
            description = "example description",
        )
    }
}


data class CreateOnlyProps(
    val createdBy: String,
) {
    companion object {
        val example = CreateOnlyProps(
            createdBy = "me",
        )
    }
}

data class UpdateOnlyProps(
    val updatedBy: String,
) {
    companion object {
        val example = UpdateOnlyProps(
            updatedBy = "them",
        )
    }
}


data class ReadOnlyProps(
    val createdOn: Instant,
    val updatedOn: Instant,
) {
    companion object {
        val example = ReadOnlyProps(
            createdOn = Instant.now().minusSeconds(1000000L),
            updatedOn = Instant.now(),
        )
    }
}

data class CreateSchema(
    @field:JsonUnwrapped
    val creationProps: CreateOnlyProps,

    @field:JsonUnwrapped
    val mutableProps: MutableProps,
) {
    @JsonCreator
    constructor(): this(
        creationProps = CreateOnlyProps.example,
        mutableProps = MutableProps.example,
    )
}

class ReadSchema {
    @field:JsonUnwrapped
    var mutableProps = MutableProps.example

    @field:JsonUnwrapped
    var createableProps = CreateOnlyProps.example

    @field:JsonUnwrapped
    var updateableProps = UpdateOnlyProps.example

    @field:JsonUnwrapped
    var readOnlyProps = ReadOnlyProps.example
}

class UpdateSchema {
    @field:JsonUnwrapped
    var mutableProps = MutableProps.example

    @field:JsonUnwrapped
    var updateableProps = UpdateOnlyProps.example
}