package com.rivertech.bet.game.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
data class AccountAlreadyExistsException(override val message: String) : RuntimeException()
