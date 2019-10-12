package com.capraro.order.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `initially there should be 2 orders`() {
        mvc.perform(get("/orders")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(2))
    }

    @Test
    fun `if we create another order there should now be 3 orders`() {

        val createOrderRequest =
                """{
                    "customer": "Joe Bloggs",
                    "items": [
                        {
                        "product": "Cappuccino",
                        "quantity": 2,
                        "coffeeSize": "SMALL",
                        "milk": "SEMI"
                        }
                    ]
                    }""".trimIndent()

        mvc.perform(post("/orders")
                .content(createOrderRequest)
                .accept("application/hal+json")
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated)

        mvc.perform(get("/orders")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(3))
    }

    @Test
    fun `status of the order 456c should be DELIVERED`() {
        val result = mvc.perform(get("/orders/456c/orderStatus")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andReturn()

        Assertions.assertThat(result.response.contentAsString).isEqualToIgnoringCase("DELIVERED")
    }

    @Test
    fun `If we pay completely the order 123a, its status should be PAID`() {

        mvc.perform(post("/orders/123a/payment")
                .param("amount", "20.0")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andReturn()

        val result = mvc.perform(get("/orders/123a/orderStatus")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andReturn()

        Assertions.assertThat(result.response.contentAsString).isEqualToIgnoringCase("PAID")
    }

    //you should also test delete and deliver
    //etc...
}