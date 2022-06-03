package kz.alimgaziyev.bankappspringbooth2.services.depositservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.TransactionType;
import kz.alimgaziyev.bankappspringbooth2.database.AccountDAO;
import kz.alimgaziyev.bankappspringbooth2.database.TransactionDOA;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDOA transactionDOA;

    public AccountDepositServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public ResponseEntity<String> deposit(String accountId, String clientId, Double amount) {
        Account account;
        try {
            account = accountDAO.findAccountByAccountIdAndClientId(accountId, clientId);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_FOUNDED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_FOUNDED);
        }
        if (amount > 0.0) {
            account.setBalance(account.getBalance() + amount);
            accountDAO.save(account);
            Transaction transaction = Transaction.builder()
                    .accountId(accountId)
                    .amount(amount)
                    .transactionType(TransactionType.DEPOSIT)
                    .date(LocalDate.now().toString())
                    .isTransferred(true)
                    .build();
            transactionDOA.save(transaction);
        } else {
            transactionDOA.save(Transaction.builder()
                    .accountId(accountId)
                    .amount(amount)
                    .transactionType(TransactionType.DEPOSIT)
                    .date(LocalDate.now().toString())
                    .isTransferred(false)
                    .build());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_TRANSACTION_FAILED);
        }
        return ResponseEntity.status(HttpStatus.OK).body(String.format("deposit to %s %s $%.2f", accountId, Messages.ACCOUNT_TRANSACTION_OK, amount));
    }
}
