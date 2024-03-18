package com.rivertech.bet.game.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class AccountNotFoundException(override val message: String) : RuntimeException()
