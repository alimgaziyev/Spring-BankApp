package kz.alimgaziyev.bankappspringbooth2.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.alimgaziyev.bankappspringbooth2.bank.Bank;
import kz.alimgaziyev.bankappspringbooth2.dto.AccountDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransferDto;
import kz.alimgaziyev.bankappspringbooth2.dto.WithdrawDepositDto;
import kz.alimgaziyev.bankappspringbooth2.models.User;
import kz.alimgaziyev.bankappspringbooth2.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/accounts")
@SecurityRequirement(name = "basicauth")
@RequiredArgsConstructor
public class AccountController {
    private final Bank bank;
    private final UserRepo userRepo;
    @GetMapping()
    public ResponseEntity<?> getAccounts(Principal principal) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getClientAccounts(user.getId());
    }
    @PostMapping
    public ResponseEntity<?> createAccount(Principal principal, @RequestBody AccountDto account) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        account.setClientId(user.getId());
        return bank.createNewAccount(account);
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getClientAccount(accountId, user.getId());
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.deleteAccount(accountId, user.getId());
    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdrawOperation(Principal principal,
                                               @PathVariable String accountId,
                                               @RequestBody WithdrawDepositDto withdrawDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        withdrawDto.setClientId(user.getId());
        return bank.withdrawOperation(accountId, withdrawDto);
    }
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<?> depositOperation(Principal principal,
                                              @PathVariable String accountId,
                                              @RequestBody WithdrawDepositDto depositDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        depositDto.setClientId(user.getId());
        return bank.depositOperation(accountId, depositDto);
    }
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getTransactionsByAccountId(Principal principal, @PathVariable String accountId) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.getTransactionsByAccountIdAndClientId(accountId, user.getId());
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<?> transferFromOneToAnotherAccount(Principal principal,
                                                             @PathVariable String accountId,
                                                             @RequestBody TransferDto transferDto) {
        User user = userRepo.findUserByEmail(principal.getName()).orElseThrow();
        return bank.transferFromOneToAnotherAccount(user.getId(), accountId, transferDto);
    }
}
