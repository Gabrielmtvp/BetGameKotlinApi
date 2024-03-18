package com.rivertech.bet.game.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.rivertech.bet.game.dtos.RequestAccountBets
import com.rivertech.bet.game.dtos.RequestBet
import com.rivertech.bet.game.enums.BetType
import com.rivertech.bet.game.models.Account
import com.rivertech.bet.game.models.Bet
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BetControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun it_should_successfully_register_bet() {
        val newBet = RequestBet(1, 10.0, 7)

        val performBet = mockMvc.post("/api/bet") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBet)
        }

        performBet
            .andDo { print() }
            .andExpect {
                status { isCreated() }
                jsonPath("$.betType") { Matchers.anyOf(
                    Matchers.equalTo("WIN"),
                    Matchers.equalTo("LOSS")
                ) }
            }
    }

    @Test
    fun it_should_return_the_account_bets() {
        val accountId = RequestAccountBets(1)

        val performGet = mockMvc.get("/api/bets") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(accountId)
        }

        performGet
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.[0].betType") { Matchers.anyOf(
                    Matchers.equalTo("WIN"),
                    Matchers.equalTo("LOSS")
                ) }
            }
    }
}