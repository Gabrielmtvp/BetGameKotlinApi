package com.rivertech.bet.game.dtos

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class RequestBet(
    @field:NotNull(message = "Account Id is required")
    val accountId: Long,

    @field:NotNull(message = "Bet amount is required")
    @field:Positive(message = "Bet amount must be greater than 0")
    val bet: Double,

    @field:NotNull(message = "Chosen number is required")
    @field:Positive(message = "Chosen number must be greater than 0")
    val chosenNumber: Int,
)
