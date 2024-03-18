package com.rivertech.bet.game.services

import com.rivertech.bet.game.models.Bet

interface GameService {

    fun calculateWinnings(chosenNumber: Int, randomNumber: Int, betAmount: Double): Double

}