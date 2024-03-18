package com.rivertech.bet.game.services

import com.rivertech.bet.game.dtos.ResponseLeaderBoard
import com.rivertech.bet.game.models.Account

interface AccountService {

    fun register(account: Account): Account

    fun findTopLeaderBoard(): List<ResponseLeaderBoard>

}