package com.example.jsonserver.api.javavars

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

    @Test
    fun `should deserialize holder`() {

        val actual = objectMapper.readValue("""{"details":"gory","title":"title","description":"desc"}""", Holder::class.java)

        val expected = Holder().apply {
            base = Base().apply {
                title = "title"
                description = "desc"
            }
            details = "gory"
        }

        assertThat(actual).isEqualTo(expected)
    }

    // ser with null :thinkingface:


    private val objectMapper = ObjectMapper()
}