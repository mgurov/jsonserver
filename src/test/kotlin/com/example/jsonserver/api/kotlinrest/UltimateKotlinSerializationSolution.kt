package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant


class UltimateKotlinSerializationSolution {

    @Test
    fun `should serialize base`() {
        val given = ReadSchema()
        given.updateableProps = UpdateOnlyProps(
            updatedBy = "me@here"
        )
        given.createableProps = CreateOnlyProps(
            createdBy = "them@there"
        )
        given.mutableProps = MutableProps(
            name = "naam",
            description = "опис"
        )
        given.readOnlyProps = ReadOnlyProps(
            createdOn = Instant.parse("2020-01-01T00:00:00Z"),
            updatedOn = Instant.parse("2021-11-11T11:11:11Z"),
        )


        val actual = objectMapper.writeValueAsString(given)

        Assertions.assertThat(actual).isEqualTo("""{"title":"title","description":"desc"}""")
    }

    private val objectMapper = jacksonObjectMapper()
        .registerModules(JavaTimeModule())

}