package kz.alimgaziyev.bankappspringbooth2.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.alimgaziyev.bankappspringbooth2.bank.Bank;
import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.dto.AccountDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransactionDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransferDto;
import kz.alimgaziyev.bankappspringbooth2.dto.WithdrawDepositDto;
import kz.alimgaziyev.bankappspringbooth2.models.User;
import kz.alimgaziyev.bankappspringbooth2.repository.UserRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@SecurityRequirement(name = "basicauth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountController {
    @Autowired
    Bank bank;
    static long clientId = 1l;
    @Autowired
    UserRepo userRepo;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(Principal principal) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getClientAccounts(user.getId());
    }
    @PostMapping
    public ResponseEntity<String> createAccount(Principal principal, @RequestBody AccountDto account) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        account.setClientId(user.getId());
        return bank.createNewAccount(account);
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountById(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getClientAccount(accountId, user.getId());
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.deleteAccount(accountId, user.getId());
    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawOperation(Principal principal, @PathVariable String accountId, @RequestBody WithdrawDepositDto withdrawDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        withdrawDto.setClientId(user.getId());
        return bank.withdrawOperation(accountId, withdrawDto);
    }
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositOperation(Principal principal, @PathVariable String accountId, @RequestBody WithdrawDepositDto depositDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        depositDto.setClientId(user.getId());
        return bank.depositOperation(accountId, depositDto);
    }
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getTransactionsByAccountIdAndClientId(accountId, user.getId());
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<?> transferFromOneToAnotherAccount(Principal principal, @PathVariable String accountId, @RequestBody TransferDto transferDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.transferFromOneToAnotherAccount(user.getId(), accountId, transferDto);
    }
}
