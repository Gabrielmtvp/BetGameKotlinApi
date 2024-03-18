package com.rivertech.bet.game.dtos

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class RequestAccountBets(
    @field:NotNull(message = "Bet amount is required")
    @field:Positive(message = "Bet amount must be greater than 0")
    val accountId: Long
)
