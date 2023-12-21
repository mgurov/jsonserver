package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonUnwrapped
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import java.util.*

// TODO: test schema
// TODO: rename CU representations to commands
// TODO: nest?

@Schema(description = "Boom!")
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

@Schema(
    description = "`NOTE!` Either `a` or `b` must be provided!",
)
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

@Schema(
    description = "All fields are optional for update, all of them - even those marked by a `*`"
)
// TODO: requiredMode = Schema.RequiredMode.NOT_REQUIRED ?
data class UpdateRepresentation(
    @field:JsonUnwrapped
    val mutableProps: MutableProps,
    @field:JsonUnwrapped
    val updateableProps: UpdateOnlyProps,
) {
    @JsonCreator
    private constructor(): this(
        mutableProps = MutableProps.example,
        updateableProps = UpdateOnlyProps.example,
    )
}