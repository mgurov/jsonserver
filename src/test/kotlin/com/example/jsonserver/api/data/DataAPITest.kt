package com.example.jsonserver.api.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataAPITest(
    @Autowired
    private val restTemplate: TestRestTemplate,
) {

    @Test
    fun `should be able to call thyself`() {
        val response = restTemplate.getForEntity(
            "/api/data/15", String::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(response.body).isEqualTo("blah")

    }

}