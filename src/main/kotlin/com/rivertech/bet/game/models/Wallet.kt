package com.rivertech.bet.game.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "wallets")
class Wallet {

    constructor(balance: Double, accountId: Long) {
        this.balance = balance
        this.accountId = accountId
        this.createdAt = LocalDateTime.now()
        this.updatedAt = LocalDateTime.now()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0;

    @Column(name = "balance", nullable = false)
    var balance: Double = 0.0

    @OneToOne()
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    var account: Account? = null

    @Column(name = "account_id", nullable = true)
    var accountId: Long = 0

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = LocalDateTime.now()
}