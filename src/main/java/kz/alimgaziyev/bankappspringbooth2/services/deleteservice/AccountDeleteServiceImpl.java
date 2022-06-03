package kz.alimgaziyev.bankappspringbooth2.services.deleteservice;

import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.database.AccountDAO;
import kz.alimgaziyev.bankappspringbooth2.database.TransactionDOA;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDeleteServiceImpl implements AccountDeleteService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    TransactionDOA transactionDOA;
    @Override
    public void delete(String accountId) {
        accountDAO.deleteAccountByAccountId(accountId);
        transactionDOA.deleteTransactionsByAccountId(accountId);
    }
}
