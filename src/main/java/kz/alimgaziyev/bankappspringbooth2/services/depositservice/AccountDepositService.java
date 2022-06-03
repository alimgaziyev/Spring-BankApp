package kz.alimgaziyev.bankappspringbooth2.services.depositservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountDepositService {
    ResponseEntity<String> deposit(String accountId, String clientId, Double amount);
}
