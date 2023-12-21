package com.example.jsonserver.api.kotlinrest

import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.UUID

@RestController
class KotlinRestApi{

    //TODO: domain object
    val data = mutableListOf<ReadRepresentation>()

    @PostMapping("/api/kotlin-rest/")
    fun create(
        @RequestBody creationRequest: CreateRepresentation
    ): ReadRepresentation {
        val newEntry = ReadRepresentation(
            mutableProps = creationRequest.mutableProps,
            createableProps = creationRequest.creationProps,
            updateableProps = UpdateOnlyProps(creationRequest.creationProps.createdBy),
            readOnlyProps = ReadOnlyProps(
                id = UUID.randomUUID().toString(),
                createdOn = Instant.now(),
                updatedOn = Instant.now(),
            )
        )
        data += newEntry
        return newEntry
    }

    @GetMapping("/api/kotlin-rest/{id}")
    fun create(
        @PathVariable id: String,
    ): ReadRepresentation {
        TODO()
    }

    @PatchMapping("/api/kotlin-rest/{id}")
    fun update(
        @PathVariable id: String,
    ): ReadRepresentation {
        TODO()
    }


}