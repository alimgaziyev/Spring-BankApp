package kz.alimgaziyev.bankappspringbooth2.controllers;


import kz.alimgaziyev.bankappspringbooth2.bank.Bank;
import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.dto.AccountDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransactionDto;
import kz.alimgaziyev.bankappspringbooth2.dto.WithdrawDepositDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountController {
    @Autowired
    Bank bank;
    static long clientId = 1l;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts() {
        return bank.getClientAccounts(clientId);
    }
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDto account) {
        return bank.createNewAccount(account);
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable String accountId) {
        return bank.getClientAccount(accountId, clientId);
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountId) {
        return bank.deleteAccount(accountId);
    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawOperation(@PathVariable String accountId, @RequestBody WithdrawDepositDto withdrawDto) {
        return bank.withdrawOperation(accountId, withdrawDto);
    }
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositOperation(@PathVariable String accountId, @RequestBody WithdrawDepositDto depositDto) {
        return bank.depositOperation(accountId, depositDto);
    }
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable String accountId) {
        return bank.getTransactionsByAccountId(accountId);
    }

}
