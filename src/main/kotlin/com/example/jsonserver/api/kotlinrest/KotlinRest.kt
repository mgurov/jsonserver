package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.Instant
import java.util.UUID

data class MutableProps(
    val name: String,
    val description: String?,
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
    val id: String,
    val createdOn: Instant,
    val updatedOn: Instant,
) {
    companion object {
        val example = ReadOnlyProps(
            id = UUID.randomUUID().toString(),
            createdOn = Instant.now().minusSeconds(1000000L),
            updatedOn = Instant.now(),
        )
    }
}

data class CreateRepresentation(
    @field:JsonUnwrapped
    val creationProps: CreateOnlyProps,

    @field:JsonUnwrapped
    val mutableProps: MutableProps,
) {
    @JsonCreator
    private constructor(): this(
        creationProps = CreateOnlyProps.example,
        mutableProps = MutableProps.example,
    )
}

data class ReadRepresentation(
    @field:JsonUnwrapped
    val mutableProps: MutableProps = MutableProps.example,

    @field:JsonUnwrapped
    val createableProps: CreateOnlyProps = CreateOnlyProps.example,

    @field:JsonUnwrapped
    val updateableProps: UpdateOnlyProps = UpdateOnlyProps.example,

    @field:JsonUnwrapped
    val readOnlyProps: ReadOnlyProps = ReadOnlyProps.example,
) {
    @JsonCreator
    private constructor(): this(
        mutableProps = MutableProps.example,
        createableProps = CreateOnlyProps.example,
        updateableProps = UpdateOnlyProps.example,
        readOnlyProps = ReadOnlyProps.example,
    )
}

//TODO: to data class
class UpdateRepresentation {
    @field:JsonUnwrapped
    var mutableProps = MutableProps.example

    @field:JsonUnwrapped
    var updateableProps = UpdateOnlyProps.example
}