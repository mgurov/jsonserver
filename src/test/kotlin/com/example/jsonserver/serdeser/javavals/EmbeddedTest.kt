package com.example.jsonserver.serdeser.javavals

import com.example.jsonserver.serdeser.javavals.Base
import com.example.jsonserver.serdeser.javavals.Holder
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmbeddedTest {
    @Test
    fun `should serialize base`() {
        val base = Base("title", "desc")

        val actual = objectMapper.writeValueAsString(base)

        assertThat(actual).isEqualTo("""{"title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize base`() {
        val actual: Base = objectMapper.readValue("""{"title":"title","description":"desc"}""", Base::class.java)

        assertThat(actual).isEqualTo(Base("title", "desc"))
    }

    @Test
    fun `should serialize holder`() {
        val value = Holder(
            "gory",
            Base("title", "desc")
        )

        val actual = objectMapper.writeValueAsString(value)

        assertThat(actual).isEqualTo("""{"details":"gory","title":"title","description":"desc"}""")
    }

    @Test
    fun `should deserialize holder`() {

        val actual = objectMapper.readValue("""{"details":"gory","title":"title","description":"desc"}""", Holder::class.java)

        val expected = Holder(
            "gory",
            Base("title", "desc")
        )

        assertThat(actual).isEqualTo(expected)
    }

    private val objectMapper = ObjectMapper()
}