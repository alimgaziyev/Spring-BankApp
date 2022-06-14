package kz.alimgaziyev.bankappspringbooth2.services.withdrawservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.TransactionType;
import kz.alimgaziyev.bankappspringbooth2.repository.AccountRepo;
import kz.alimgaziyev.bankappspringbooth2.repository.TransactionRepo;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public ResponseEntity<String> withdraw(String accountId, String clientId, Double amount) throws IllegalArgumentException {
        Account account;
        account = accountRepo.findAccountByAccountIdAndClientId(accountId, clientId);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_FOUNDED);
            }
            if (!account.getIsWithdrawAllowed()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_WITHDRAW_NOT_ALLOWED);
            }
        Double current = account.getBalance();
        ResponseEntity<String> ans;
        Transaction transaction = Transaction.builder()
                .clientId(clientId)
                .accountId(accountId)
                .amount(amount)
                .transactionType(TransactionType.WITHDRAW)
                .date(LocalDate.now().toString())
                .build();
        if (amount > 0.0 && current - amount >= 0.0) {
            account.setBalance(current - amount);
            accountRepo.save(account);
            transaction.setTransferred(true);
            transactionRepo.save(transaction);
            ans = ResponseEntity.status(HttpStatus.OK).body(String.format("withdraw from %s %s $%.2f", accountId, Messages.ACCOUNT_TRANSACTION_OK, amount));
        } else {
            transaction.setTransferred(false);
            transactionRepo.save(transaction);
            ans = ResponseEntity.status(HttpStatus.OK).body(String.format("%s from id = %s NOT Enough money", Messages.ACCOUNT_TRANSACTION_FAILED, accountId));
        }
        return ans;
    }
}
