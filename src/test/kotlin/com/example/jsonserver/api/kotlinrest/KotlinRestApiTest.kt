package com.example.jsonserver.api.kotlinrest

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Read
import org.assertj.core.api.Assertions
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

    fun <T> ResponseEntity<T>.okBody(): T {
        assertThat(this.statusCode).isEqualTo(HttpStatus.OK)
        return this.body!!
    }
}