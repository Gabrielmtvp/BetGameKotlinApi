package com.rivertech.bet.game.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime

@Entity
@Table(name = "accounts", uniqueConstraints = [UniqueConstraint(columnNames = ["wallet_id"])])
class Account {

    constructor(name: String, surname: String, username: String) {
        this.name = name
        this.surname = surname
        this.username = username
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "surname", nullable = false)
    var surname: String = ""

    @Column(name = "username", nullable = false)
    var username: String = ""

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

}