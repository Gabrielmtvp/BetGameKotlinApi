package com.rivertech.bet.game.repositories

import com.rivertech.bet.game.models.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository: JpaRepository<Wallet, Long> {

    fun findByAccountId(accountId: Long): Wallet

}