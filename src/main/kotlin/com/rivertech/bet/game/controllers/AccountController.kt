package com.rivertech.bet.game.controllers

import com.rivertech.bet.game.models.Account
import com.rivertech.bet.game.services.implementations.AccountServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = ["*"])
class AccountController(private val service: AccountServiceImpl) {

    @PostMapping("register")
    fun register(@RequestBody account: Account): ResponseEntity<out Any> {
        return try {
            ResponseEntity( service.register(account), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body<Any>(e.message)
        }
    }

    @GetMapping("leaderboard")
    fun leaderboard(): ResponseEntity<out Any> {
        return try {
            ResponseEntity( service.findTopLeaderBoard(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body<Any>(e.message)
        }
    }

}