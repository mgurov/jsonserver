package com.example.jsonserver.api.data

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DataAPI {

    @GetMapping("/api/data/{size}")
    fun getData(
        @PathVariable
        size: Int,
        @RequestParam(required = false, defaultValue = "10")
        pageSize: Int,
        @RequestParam(required = false)
        nextPage: String?,
    ) : Response {

        val data = (1..size).map { it.toString() }

        return Response(data, nextPage = null, pageSize = pageSize)

    }

    data class Response(
        val data: List<String>,
        val pageSize: Int,
        val nextPage: String?,
    )
}