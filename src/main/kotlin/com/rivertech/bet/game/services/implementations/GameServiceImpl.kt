package com.rivertech.bet.game.services.implementations

import com.rivertech.bet.game.enums.BetType
import com.rivertech.bet.game.exceptions.AccountNotFoundException
import com.rivertech.bet.game.exceptions.InsufficientBetBalanceException
import com.rivertech.bet.game.models.Bet
import com.rivertech.bet.game.repositories.AccountRepository
import com.rivertech.bet.game.repositories.WalletRepository
import com.rivertech.bet.game.services.GameService
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class GameServiceImpl(): GameService {
    override fun calculateWinnings(chosenNumber: Int, randomNumber: Int, betAmount: Double): Double {
        return when (abs(chosenNumber - randomNumber)) {
            0 -> betAmount * 10
            1 -> betAmount * 5
            2 -> betAmount * 0.5
            else -> 0.0
        }
    }
}