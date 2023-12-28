package com.example.jsonserver

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions

//language=JSON
fun String.assertIsJson(
    expected: String
) {
    val actualJson: JsonNode = objectMapper.readTree(this)
    val expectedJson: JsonNode = objectMapper.readTree(expected)
    Assertions.assertThat(actualJson).isEqualTo(expectedJson)
}

private val objectMapper = jacksonObjectMapper()
    .registerModules(JavaTimeModule())
