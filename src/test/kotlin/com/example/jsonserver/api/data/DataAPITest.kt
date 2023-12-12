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
            "/api/data/3?pageSize=2",DataAPI.Response::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.data).containsExactly("1", "2")
        assertThat(response.body?.pageSize).isEqualTo(2)
        assertThat(response.body?.nextPage).isNotNull()

        val url = "/api/data/3?pageSize=2&nextPage=${response.body?.nextPage}"
        val response2 = restTemplate.getForEntity(
            url,DataAPI.Response::class.java
        )

        assertThat(response2.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response2.body?.data).containsExactly("3")
        assertThat(response2.body?.pageSize).isEqualTo(2)
        assertThat(response2.body?.nextPage).isNull()
    }

}