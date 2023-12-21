package com.example.jsonserver.api.kotlinrest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinRestApiTest(
    @Autowired
    private val restTemplate: TestRestTemplate
) {

    @Test
    fun `should be able to create an entity`() {
        val request = CreateRepresentation(
            creationProps = CreateOnlyProps(
                createdBy = "test"
            ),
            mutableProps = MutableProps(
                name = "testing",
                description = null
            )
        )
        val created = restTemplate.postForEntity(
            "/api/kotlin-rest/",
            request,
            ReadRepresentation::class.java
        ).okBody()

        assertThat(created.updateableProps.updatedBy).isEqualTo("test")
    }

    @Test
    fun `simple name update`() {
        val created = givenEntityCreated(name = "initial", description = "initial")

        assertThat(created.mutableProps).isEqualTo(MutableProps(
            name = "initial",
            description = "initial"
        ))

        val updated = whenUpdated(created, """
            {"name": "updated"}
        """)

        assertThat(updated.updateableProps).isEqualTo(
            MutableProps(
                name = "initial",
                description = "initial"
            )
        )
    }

    private fun whenUpdated(created: ReadRepresentation, updateJson: String): ReadRepresentation {
        val jsonNode = objectMapper.readTree(updateJson)
        return restTemplate.patchForObject("/api/kotlin-rest/{id}", jsonNode, ReadRepresentation::class.java, created.readOnlyProps.id)
    }

    val objectMapper = jacksonObjectMapper()

    fun givenEntityCreated(
        name: String = "testing",
        description: String? = null,
        createdBy: String = "test"
    ): ReadRepresentation {
        val request = CreateRepresentation(
            creationProps = CreateOnlyProps(
                createdBy = createdBy
            ),
            mutableProps = MutableProps(
                name = name,
                description = description
            )
        )
        return restTemplate.postForEntity(
            "/api/kotlin-rest/",
            request,
            ReadRepresentation::class.java
        ).okBody()
    }


    fun <T> ResponseEntity<T>.okBody(): T {
        assertThat(this.statusCode).isEqualTo(HttpStatus.OK)
        return this.body!!
    }
}