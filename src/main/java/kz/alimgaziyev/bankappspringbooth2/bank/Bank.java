package kz.alimgaziyev.bankappspringbooth2.bank;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.dto.AccountDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransactionDto;
import kz.alimgaziyev.bankappspringbooth2.dto.TransferDto;
import kz.alimgaziyev.bankappspringbooth2.dto.WithdrawDepositDto;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import kz.alimgaziyev.bankappspringbooth2.services.creationservice.AccountCreationService;
import kz.alimgaziyev.bankappspringbooth2.services.deleteservice.AccountDeleteService;
import kz.alimgaziyev.bankappspringbooth2.services.depositservice.AccountDepositService;
import kz.alimgaziyev.bankappspringbooth2.services.listingservice.AccountListingService;
import kz.alimgaziyev.bankappspringbooth2.services.listingtransactions.TransactionListingService;
import kz.alimgaziyev.bankappspringbooth2.services.transferservice.TransferService;
import kz.alimgaziyev.bankappspringbooth2.services.withdrawservice.AccountWithdrawService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Bank {
    @NonFinal
    static Long bank_id = 1l;
    @NonFinal
    static Long lastBankAccountNumber = 1l;
    AccountCreationService accountCreationService;
    AccountDeleteService accountDeleteService;
    AccountListingService accountListingService;
    AccountWithdrawService accountWithdrawService;
    AccountDepositService accountDepositService;
    TransactionListingService transactionListingService;
    TransferService transferService;


    public ResponseEntity<List<Account>> getClientAccounts(Long clientId) {
        ResponseEntity<List<Account>> ans;
        List<Account> list = null;
        try {
            list = accountListingService.getClientAccounts(clientId.toString());
            ans = ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            ans = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(list);
        }
        return ans;
    }
    public ResponseEntity<Object> getClientAccount(String accountId, Long clientId) {
        ResponseEntity<Object> ans;
        Account account;
            account = accountListingService.getClientAccount(accountId, clientId.toString());
            if (account != null) {
                ans = ResponseEntity.status(HttpStatus.OK).body(account);
            } else {
                ans = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Messages.ACCOUNT_NOT_FOUNDED);
            }
        return ans;
    }
    public ResponseEntity<String> createNewAccount(AccountDto accountDto) {
        ResponseEntity<String> ans;
        String accountId = String.format("%03d%06d", bank_id,lastBankAccountNumber);
        try {;
            accountCreationService.create(accountId, accountDto.getClientId(), accountDto.getAccountType());
            incrementLastAccountNumber();
            ans = ResponseEntity.status(HttpStatus.CREATED).body(String.format("%s id: %s",Messages.ACCOUNT_CREATED, accountId));
        } catch (Exception e) {
            ans = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_CREATED);
        }
        return ans;
    }
    public ResponseEntity<String> deleteAccount(String accountId, Long clientId) {
        ResponseEntity<String> ans;
        try {
            accountDeleteService.delete(accountId, clientId.toString());
            ans = ResponseEntity.status(HttpStatus.OK).body(String.format("%s, Account id is %s", Messages.ACCOUNT_DELETED, accountId));
        } catch (Exception e) {
            ans = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.ACCOUNT_NOT_FOUNDED);
        }
        return ans;
    }
    public ResponseEntity<String> withdrawOperation(String accountId, WithdrawDepositDto withdrawDto) {
        return accountWithdrawService.withdraw(accountId, withdrawDto.getClientId().toString(), withdrawDto.getAmount());
    }
    public ResponseEntity<String> depositOperation(String accountId, WithdrawDepositDto withdrawDto) {
        return accountDepositService.deposit(accountId, withdrawDto.getClientId().toString(), withdrawDto.getAmount());
    }
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountIdAndClientId(String accountId, Long clientId) {
        return transactionListingService.getTransactionsByAccountIdAndClientId(accountId, clientId.toString());
    }

    public ResponseEntity<?> transferFromOneToAnotherAccount(Long clientId, String accountId, TransferDto transferDto) {
        ResponseEntity<?> ans;
        try {
            transferService.transferFromOneToAnotherAccount(clientId.toString(),
                                                            accountId,
                                                            transferDto.getToAccountId(),
                                                            transferDto.getAmount());
            ans = ResponseEntity.ok("Transferred");
        } catch(Exception e) {
            ans = ResponseEntity.badRequest().body("Operation failed");
        }
        return ans;
    }

    private void incrementLastAccountNumber() {
        lastBankAccountNumber++;
    }
}
