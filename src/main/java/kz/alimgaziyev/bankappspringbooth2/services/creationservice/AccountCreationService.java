package kz.alimgaziyev.bankappspringbooth2.services.creationservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.AccountType;
import org.springframework.stereotype.Service;

@Service
public interface AccountCreationService {
    public void create(String accountId, String clientId, AccountType accountType);
}