package com.rivertech.bet.game.repositories

import com.rivertech.bet.game.dtos.ResponseLeaderBoard
import com.rivertech.bet.game.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository: JpaRepository<Account, Long> {

    fun findByUsername(username: String): Optional<Account>

    @Query(
        value = "select sum(bet_result), acc.name from bets inner join accounts acc on acc.id = bets.account_id where transaction_type = 0 group by account_id, acc.name order by sum desc LIMIT 5",
        nativeQuery = true
    )
    fun findTopLeaderBoard(): List<ResponseLeaderBoard>

}