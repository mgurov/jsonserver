package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.math.exp


class UltimateKotlinSerializationSolution {

    @Test
    fun `should serialize base`() {
        val given = ReadSchema()
        given.mutableProps = MutableProps(
            name = "naam",
            description = "опис"
        )
        given.createableProps = CreateOnlyProps(
            createdBy = "them@there"
        )
        given.updateableProps = UpdateOnlyProps(
            updatedBy = "me@here"
        )
        given.readOnlyProps = ReadOnlyProps(
            createdOn = Instant.parse("2020-01-01T00:00:00Z"),
            updatedOn = Instant.parse("2021-11-11T11:11:11Z"),
        )

        val actual = objectMapper.writeValueAsString(given)


        //language=JSON
        actual.assertIsJson(
            """
                {
                "name":"naam",
                "description":"опис",
                "createdBy":"them@there",
                "updatedBy":"me@here",
                "createdOn":"2020-01-01T00:00:00Z",
                "updatedOn":"2021-11-11T11:11:11Z"
            }"""
        )
    }

    fun String.assertIsJson(
        expected: String
    ) {
        val actualJson: JsonNode = objectMapper.readTree(this)
        val expectedJson: JsonNode = objectMapper.readTree(expected)
        assertThat(actualJson).isEqualTo(expectedJson)
    }

    private val objectMapper = jacksonObjectMapper()
        .registerModules(JavaTimeModule())
        .apply { disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) }
}