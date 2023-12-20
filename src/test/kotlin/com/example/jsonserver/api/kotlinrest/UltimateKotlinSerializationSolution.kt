package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant


class UltimateKotlinSerializationSolution {

    @Test
    fun `should serialize read representation`() {

        val given = ReadRepresentation().apply {
            mutableProps = MutableProps(
                name = "naam",
                description = "опис"
            )
            createableProps = CreateOnlyProps(
                createdBy = "them@there"
            )
            updateableProps = UpdateOnlyProps(
                updatedBy = "me@here"
            )
            readOnlyProps = ReadOnlyProps(
                id = "a random id",
                createdOn = Instant.parse("2020-01-01T00:00:00Z"),
                updatedOn = Instant.parse("2021-11-11T11:11:11Z"),
            )
        }

        val actual = objectMapper.writeValueAsString(given)
        //language=JSON
        actual.assertIsJson(
            """
                {
                "id":"a random id",
                "name":"naam",
                "description":"опис",
                "createdBy":"them@there",
                "updatedBy":"me@here",
                "createdOn":"2020-01-01T00:00:00Z",
                "updatedOn":"2021-11-11T11:11:11Z"
            }"""
        )
    }

    @Test
    fun `should deserialize create entity`() {
        //language=JSON
        val givenJson = """
            {
            "name":"naam",
            "description":"опис",
            "createdBy":"them@there",
            "updatedBy":"me@here",
            "createdOn":"2020-01-01T00:00:00Z",
            "updatedOn":"2021-11-11T11:11:11Z"
            }""".trimIndent()

        val actual: CreateRepresentation = objectMapper.readValue(givenJson)

        assertThat(actual).isEqualTo(
            CreateRepresentation(
                creationProps = CreateOnlyProps(
                    createdBy = "them@there",
                ),
                mutableProps = MutableProps(
                    name = "naam",
                    description = "опис",
                )
            )
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