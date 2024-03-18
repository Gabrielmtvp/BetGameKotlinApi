package com.rivertech.bet.game.services

import com.rivertech.bet.game.dtos.RequestAccountBets
import com.rivertech.bet.game.dtos.RequestBet
import com.rivertech.bet.game.models.Bet

interface BetService {

    fun placeBet(requestBet: RequestBet): Bet

    fun getBets(requestAccountBet: RequestAccountBets): List<Bet>

}