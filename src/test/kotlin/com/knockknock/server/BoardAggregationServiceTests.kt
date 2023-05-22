package com.knockknock.server

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals

@SpringBootTest
@ExtendWith(SpringExtension::class)
class BoardAggregationServiceTests {

    @Test
    fun boardTest() {
        val test = true;

        assertEquals(test,true)
    }
}