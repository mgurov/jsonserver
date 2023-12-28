package com.example.jsonserver.serdeser.nested

import com.example.jsonserver.assertIsJson
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test

class NestedTest {
    @Test
    fun `should serialize base`() {
        val base = Base(
            title = "title",
            description = "desc",
            name = Nameable(
                name = "naam",
                type = "soort"
            )
        )

        val actual = objectMapper.writeValueAsString(base)

        actual.assertIsJson("""{"title":"title","description":"desc", "name": {"name": "naam", "type": "soort"}}""")
    }

//    @Test
//    fun `should deserialize base`() {
//        val actual: Base = objectMapper.readValue("""{"title":"title","description":"desc"}""", Base::class.java)
//
//        assertThat(actual).isEqualTo(
//            Base(
//            title = "title",
//            description = "desc",
//        )
//        )
//    }
//
//    @Test
//    fun `should serialize holder`() {
//        val value = Holder(
//            details = "gory",
//        ).apply {
//            base = Base(
//                title = "title",
//                description = "desc",
//            )
//        }
//
//        val actual = objectMapper.writeValueAsString(value)
//
//        assertThat(actual).isEqualTo("""{"details":"gory","title":"title","description":"desc"}""")
//    }
//
//    @Test
//    fun `should deserialize holder`() {
//
//        val actual = objectMapper.readValue("""{"details":"gory","title":"title","description":"desc"}""", Holder::class.java)
//
//        val expected = Holder(
//            details = "gory",
//        ).apply {
//            base = Base(
//                title = "title",
//                description = "desc",
//            )
//        }
//
//        assertThat(actual).isEqualTo(expected)
//    }

    private val objectMapper = jacksonObjectMapper()
}