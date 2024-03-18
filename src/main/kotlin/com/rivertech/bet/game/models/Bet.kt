package com.rivertech.bet.game.models

import com.rivertech.bet.game.enums.BetType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bets")
class Bet {

    constructor(betType: BetType,
                betAmount: Double,
                balanceBefore: Double,
                balanceAfter: Double,
                chosenNumber: Int,
                resultNumber: Int,
                betResult: Double,
                accountId: Long
                ) {
        this.betType = betType
        this.betAmount = betAmount
        this.chosenNumber = chosenNumber
        this.resultNumber = resultNumber
        this.balanceBefore = balanceBefore
        this.balanceAfter = balanceAfter
        this.betResult = betResult
        this.accountId = accountId
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(name = "transaction_type", nullable = false)
    var betType: BetType? = null

    @Column(name = "bet_amount", nullable = false)
    var betAmount: Double = 0.0

    @Column(name = "balance_before", nullable = false)
    var balanceBefore: Double = 0.0

    @Column(name = "balance_after", nullable = false)
    var balanceAfter: Double = 0.0

    @Column(name = "chosen_number", nullable = false)
    var chosenNumber: Int = 0

    @Column(name = "result_number", nullable = false)
    var resultNumber: Int = 0

    @Column(name = "bet_result", nullable = false)
    var betResult: Double = 0.0

    @ManyToOne()
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    var account: Account? = null

    @Column(name = "account_id", nullable = true)
    var accountId: Long = 0

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = LocalDateTime.now()

}