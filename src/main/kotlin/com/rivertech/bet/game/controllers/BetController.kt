package com.rivertech.bet.game.controllers

import com.rivertech.bet.game.dtos.RequestAccountBets
import com.rivertech.bet.game.dtos.RequestBet
import com.rivertech.bet.game.services.implementations.BetServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"])
class BetController(private val service: BetServiceImpl) {

    @PostMapping("bet")
    fun bet(@Valid @RequestBody requestBet: RequestBet): ResponseEntity<out Any> {
        return try {
            ResponseEntity(service.placeBet(requestBet), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body<Any>(e.message)
        }
    }

    @GetMapping("bets")
    fun bets(@Valid @RequestBody requestAccountBets: RequestAccountBets): ResponseEntity<out Any> {
        return try {
            ResponseEntity(service.getBets(requestAccountBets), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body<Any>(e.message)
        }
    }

}