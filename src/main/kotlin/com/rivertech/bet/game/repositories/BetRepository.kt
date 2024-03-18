package com.rivertech.bet.game.repositories

import com.rivertech.bet.game.models.Bet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BetRepository: JpaRepository<Bet, Long> {

    fun findByAccountId(accountId: Long): List<Bet>

}