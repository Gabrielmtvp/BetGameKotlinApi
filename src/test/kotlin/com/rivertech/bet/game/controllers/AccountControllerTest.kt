package com.rivertech.bet.game.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rivertech.bet.game.models.Account
import jdk.jfr.ContentType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.web.client.HttpClientErrorException.BadRequest

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun it_should_fail_to_register() {
        mockMvc.post("/api/account/register")
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
    }

    @Test
    fun it_should_successfully_register() {
        val newAccount = Account(name = "Alicia", surname = "Gomes", username = "aliciamtvp")

        val performPost = mockMvc.post("/api/account/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newAccount)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isCreated() }
                jsonPath("$.name") { value("Alicia") }
                jsonPath("$.surname") { value("Gomes") }
                jsonPath("$.username") { value("aliciamtvp") }
            }
    }

    @Test
    fun it_should_fail_username_already_in_use() {
        val newAccount = Account(name = "Alicia", surname = "Gomes", username = "aliciamtvp")

        val performPost = mockMvc.post("/api/account/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newAccount)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isInternalServerError() }
            }
    }
}