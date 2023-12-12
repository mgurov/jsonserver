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

    val objectMapper = ObjectMapper()
}