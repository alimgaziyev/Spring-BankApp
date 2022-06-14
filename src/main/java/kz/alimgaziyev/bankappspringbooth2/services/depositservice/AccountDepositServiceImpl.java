package kz.alimgaziyev.bankappspringbooth2.services.depositservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.TransactionType;
import kz.alimgaziyev.bankappspringbooth2.repository.AccountRepo;
import kz.alimgaziyev.bankappspringbooth2.repository.TransactionRepo;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDepositServiceImpl implements AccountDepositService {
    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    @Override
    public ResponseEntity<String> deposit(String accountId, String clientId, Double amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().body(Messages.ACCOUNT_TRANSACTION_FAILED);
        }

        Account account = accountRepo.findAccountByAccountIdAndClientId(accountId, clientId);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_FOUNDED);
        }

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction transaction = Transaction.builder()
                                            .clientId(clientId)
                                            .accountId(accountId)
                                            .amount(amount)
                                            .transactionType(TransactionType.DEPOSIT)
                                            .date(LocalDate.now().toString())
                                            .isTransferred(true)
                                            .build();
        transactionRepo.save(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("deposit to %s %s $%.2f", accountId, Messages.ACCOUNT_TRANSACTION_OK, amount));
    }
}
