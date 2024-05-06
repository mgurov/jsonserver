package com.example.jsonserver.api.data

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

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
        @RequestParam(required = false, defaultValue = "10")
        delay: Long,
        @RequestParam(required = false, defaultValue = "false")
        fail: Boolean,
    ) : Response {

        val data: List<String> = (1..size).map { it.toString() }

        val startingFrom = nextPage?.toInt() ?: 0
        val dataToStartFrom = data.drop(startingFrom)
        val dataPage = dataToStartFrom.take(pageSize)
        val nextNextPage = if (dataToStartFrom.size == dataPage.size) {
            null //consumed the reminder
        } else {
            (startingFrom + dataPage.size).toString()
        }

        TimeUnit.SECONDS.sleep(delay)

        if (fail) {
            throw RuntimeException("Duly failing as per request.")
        }

        return Response(dataPage, nextPage = nextNextPage, pageSize = pageSize)

    }

    data class Response(
        val data: List<String>,
        val pageSize: Int,
        val nextPage: String?,
    )
}