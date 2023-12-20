package com.example.jsonserver.api.kotlinrest

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

class CreateSchema {
    @field:JsonUnwrapped
    var creationProps =  CreateOnlyProps.example

    @field:JsonUnwrapped
    var mutableProps = MutableProps.example

    override fun toString(): String {
        return "CreateSchema(creationProps=$creationProps, mutableProps=$mutableProps)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreateSchema

        if (creationProps != other.creationProps) return false
        if (mutableProps != other.mutableProps) return false

        return true
    }

    override fun hashCode(): Int {
        var result = creationProps.hashCode()
        result = 31 * result + mutableProps.hashCode()
        return result
    }


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