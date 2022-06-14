package kz.alimgaziyev.bankappspringbooth2.services.deleteservice;

import kz.alimgaziyev.bankappspringbooth2.repository.AccountRepo;
import kz.alimgaziyev.bankappspringbooth2.repository.TransactionRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDeleteServiceImpl implements AccountDeleteService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @Override
    public void delete(String accountId, String clientId) {
        accountRepo.delete(accountRepo.findAccountByAccountIdAndClientId(accountId, clientId));
        transactionRepo.deleteTransactionsByAccountId(accountId);
    }
}
