package com.rivertech.bet.game.services.implementations

import com.rivertech.bet.game.dtos.RequestAccountBets
import com.rivertech.bet.game.dtos.RequestBet
import com.rivertech.bet.game.enums.BetType
import com.rivertech.bet.game.exceptions.AccountNotFoundException
import com.rivertech.bet.game.exceptions.GetBetsException
import com.rivertech.bet.game.exceptions.InsufficientBetBalanceException
import com.rivertech.bet.game.models.Bet
import com.rivertech.bet.game.repositories.AccountRepository
import com.rivertech.bet.game.repositories.BetRepository
import com.rivertech.bet.game.repositories.WalletRepository
import com.rivertech.bet.game.services.BetService
import org.springframework.stereotype.Service

@Service
class BetServiceImpl(private val gameService: GameServiceImpl,
                     private val betRepository: BetRepository,
                     private val accountRepository: AccountRepository,
                     private val walletRepository: WalletRepository): BetService {

    override fun placeBet(requestBet: RequestBet): Bet {
        val accountId = requestBet.accountId
        val betAmount = requestBet.bet
        val chosenNumber = requestBet.chosenNumber

        accountRepository
            .findById(accountId)
            .orElseThrow{ AccountNotFoundException("Account Not Found.") }

        var currentWallet = walletRepository.findByAccountId(accountId)
        var currentBalance = currentWallet.balance
        if(currentBalance < betAmount) {
            throw InsufficientBetBalanceException("Insufficient Funds.")
        }

        val randomNumber = (0..10).shuffled().last()
        val winnings = gameService.calculateWinnings(chosenNumber, randomNumber, betAmount)

        // Update wallet's user
        if(winnings > 0) {
            currentWallet.balance += winnings
        } else {
            currentWallet.balance -= betAmount
        }

        walletRepository.save(currentWallet)

        val betType: BetType = if(winnings > 0) BetType.WIN else BetType.LOSS

        // Record the bet
        val bet = Bet(betType = betType,
            betAmount = betAmount,
            balanceBefore = currentBalance,
            balanceAfter = currentWallet.balance,
            chosenNumber = chosenNumber,
            resultNumber = randomNumber,
            betResult = winnings,
            accountId = accountId)

        return betRepository.save(bet)
    }

    override fun getBets(requestAccountBet: RequestAccountBets): List<Bet> {
        val accountId = requestAccountBet.accountId
        try {
            return betRepository.findByAccountId(accountId)
        } catch (e: Exception) {
            throw GetBetsException("This username is being used already!");
        }

    }

}