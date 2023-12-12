package com.example.jsonserver.api.embedded

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmbeddedTest {
    @Test
    fun `should serialize`() {
        val base = Base().apply {
            title = "title"
            description = "desc"
        }

        val actual = objectMapper.writeValueAsString(base)

        assertThat(actual).isEqualTo("""{"title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize`() {
        val actual:Base = objectMapper.readValue("""{"title":"title","description":"desc"}""", Base::class.java)

        assertThat(actual).isEqualTo(Base().apply {
            title = "title"
            description = "desc"
        })
    }

    private val objectMapper = ObjectMapper()
}