package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.UUID

@RestController
class KotlinRestApi(
    private val objectMapper: ObjectMapper
){

    //TODO: domain object
    val data = mutableMapOf<String, ReadRepresentation>()

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
        data[newEntry.readOnlyProps.id] = newEntry
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
        @Schema(implementation = UpdateRepresentation::class)
        @RequestBody patchJson: String, //TODO: consider the full class?
    ): ReadRepresentation {

        val entry = data[id] ?: throw RuntimeException("Not found") //TODO: 404
        val updateThings = UpdateRepresentation(
            mutableProps = entry.mutableProps,
            updateableProps = entry.updateableProps,
        )

        val readerForUpdating = objectMapper.readerForUpdating(updateThings)
        val patched: UpdateRepresentation = readerForUpdating.readValue(patchJson)
        val patchedEntry = entry.copy(
            mutableProps = patched.mutableProps,
            updateableProps = patched.updateableProps,
        )
        data[id] = patchedEntry
        return patchedEntry
    }


}