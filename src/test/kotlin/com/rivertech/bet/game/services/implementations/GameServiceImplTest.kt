package com.rivertech.bet.game.services.implementations

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GameServiceImplTest {

    private val gameService = GameServiceImpl()

    @Test
    fun calculateWinningsX10() {
        val result = gameService.calculateWinnings(5, 5, 10.0)
        assertEquals(100.0, result, "The winnings should be 100.0")
    }

    @Test
    fun calculateWinningsX5() {
        val result = gameService.calculateWinnings(5, 6, 10.0)
        assertEquals(50.0, result, "The winnings should be 50.0")
    }

    @Test
    fun calculateWinningsX0_5() {
        val result = gameService.calculateWinnings(5, 7, 10.0)
        assertEquals(5.0, result, "The winnings should be 5.0")
    }

    @Test
    fun calculateWinningsX0() {
        val result = gameService.calculateWinnings(5, 8, 10.0)
        assertEquals(0.0, result, "The winnings should be 0.0")
    }
}