package kz.alimgaziyev.bankappspringbooth2.services.withdrawservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountWithdrawService {

    ResponseEntity<String> withdraw(String accountId, String clientId, Double amount) throws IllegalArgumentException;
}
