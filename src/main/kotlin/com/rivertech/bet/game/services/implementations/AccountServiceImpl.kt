package com.rivertech.bet.game.services.implementations

import com.rivertech.bet.game.dtos.ResponseLeaderBoard
import com.rivertech.bet.game.exceptions.AccountAlreadyExistsException
import com.rivertech.bet.game.models.Account
import com.rivertech.bet.game.models.Wallet
import com.rivertech.bet.game.repositories.AccountRepository
import com.rivertech.bet.game.repositories.WalletRepository
import com.rivertech.bet.game.services.AccountService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AccountServiceImpl(private val repository: AccountRepository,
                         private val walletRepository: WalletRepository): AccountService {

    override fun register(account: Account): Account {
        val accountUsername: Optional<Account> = repository.findByUsername(account.username);
        System.out.println(accountUsername.isPresent)
        if(accountUsername.isPresent) {
            throw AccountAlreadyExistsException("This username is being used already!");
        }

        val newAccount = repository.save(account)

        val wallet = Wallet(balance = 1000.0, accountId = newAccount.id)
        wallet.accountId = newAccount.id
        walletRepository.save(wallet);

        return newAccount
    }

    override fun findTopLeaderBoard(): List<ResponseLeaderBoard> {
        return repository.findTopLeaderBoard().map {
            val sum = (it[0] as Number).toDouble() // Cast to Number first to cover various numeric types
            System.out.println(sum)
            val name = it[1] as String
            ResponseLeaderBoard(sum, name)
        }
    }
}

private operator fun Any.get(i: Int) {
}
