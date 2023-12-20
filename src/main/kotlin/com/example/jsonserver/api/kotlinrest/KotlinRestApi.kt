package com.example.jsonserver.api.kotlinrest

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KotlinRestApi{

    val data = mutableListOf<ReadSchema>()

    @PostMapping("/api/kotlin-rest/")
    fun create(
        @RequestBody creationRequest: CreateSchema
    ) {
        TODO()
    }

    @PostMapping("/api/kotlin-rest/{id}")
    fun create(
        @PathVariable id: String,
    ): ReadSchema {
        TODO()
    }


}