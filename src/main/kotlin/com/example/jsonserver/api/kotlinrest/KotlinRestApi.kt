package com.example.jsonserver.api.kotlinrest

import org.springframework.web.bind.annotation.*

@RestController
class KotlinRestApi{

    val data = mutableListOf<ReadRepresentation>()

    @PostMapping("/api/kotlin-rest/")
    fun create(
        @RequestBody creationRequest: CreateRepresentation
    ) {
        TODO()
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