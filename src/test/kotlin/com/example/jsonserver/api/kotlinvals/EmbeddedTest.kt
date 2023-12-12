package com.example.jsonserver.api.kotlinvals

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmbeddedTest {
    @Test
    fun `should serialize base`() {
        val base = Base(
            title = "title",
            description = "desc",
        )

        val actual = objectMapper.writeValueAsString(base)

        assertThat(actual).isEqualTo("""{"title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize base`() {
        val actual:Base = objectMapper.readValue("""{"title":"title","description":"desc"}""", Base::class.java)

        assertThat(actual).isEqualTo(Base(
            title = "title",
            description = "desc",
        ))
    }

    @Test
    fun `should serialize holder`() {
        val value = Holder(
            base = Base(
                title = "title",
                description = "desc",
            ),
            details = "gory",
        )

        val actual = objectMapper.writeValueAsString(value)

        assertThat(actual).isEqualTo("""{"details":"gory","title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize holder`() {

        val actual = objectMapper.readValue("""{"details":"gory","title":"title","description":"desc"}""", Holder::class.java)

        val expected = Holder(
            base = Base(
                title = "title",
                description = "desc",
            ),
            details = "gory",
        )

        assertThat(actual).isEqualTo(expected)
    }

    private val objectMapper = ObjectMapper()
}