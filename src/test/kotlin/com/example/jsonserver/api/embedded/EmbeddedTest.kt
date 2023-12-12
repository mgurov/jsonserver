package com.example.jsonserver.api.embedded

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmbeddedTest {
    @Test
    fun `should serialize base`() {
        val base = Base().apply {
            title = "title"
            description = "desc"
        }

        val actual = objectMapper.writeValueAsString(base)

        assertThat(actual).isEqualTo("""{"title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize base`() {
        val actual:Base = objectMapper.readValue("""{"title":"title","description":"desc"}""", Base::class.java)

        assertThat(actual).isEqualTo(Base().apply {
            title = "title"
            description = "desc"
        })
    }

    @Test
    fun `should serialize holder`() {
        val value = Holder().apply {
            base = Base().apply {
                title = "title"
                description = "desc"
            }
            details = "gory"
        }

        val actual = objectMapper.writeValueAsString(value)

        assertThat(actual).isEqualTo("""{"details":"gory","title":"title","description":"desc"}""")
    }

    // ser with null :thinkingface:


    private val objectMapper = ObjectMapper()
}