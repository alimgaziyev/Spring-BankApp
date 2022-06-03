package kz.alimgaziyev.bankappspringbooth2.services.listingservice;

import kz.alimgaziyev.bankappspringbooth2.database.AccountDAO;
import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListingServiceImpl implements AccountListingService {
    private AccountDAO accountDAO;

    public AccountListingServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> getClientAccounts(String clientId) {
        return accountDAO.findAccountsByClientId(clientId);
    }

    @Override
    public Account getClientWithdrawAccount(String accountId, String clientId) {
        Account account = accountDAO.findAccountByAccountIdAndClientId(accountId, clientId);
        if (account.getIsWithdrawAllowed()) return account;
        return null;
    }

    @Override
    public Account getClientAccount(String accountId, String clientId) {
        return accountDAO.findAccountByAccountIdAndClientId(accountId, clientId);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountDAO.findById(accountId).get();
    }

    @Override
    public List<Account> getClientAccountsByType() {
        return null;
    }
}
