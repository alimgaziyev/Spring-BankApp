package kz.alimgaziyev.bankappspringbooth2.database;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.bank.account.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountDAO extends CrudRepository<Account, String> {
    List<Account> findAccountsByClientId(String clientId);
    List<Account> findAccountsByAccountType(AccountType accountType);
    Account findAccountByAccountIdAndIsWithdrawAllowed(String accountId, boolean isWithdrawAllowed);
    Account findAccountByAccountIdAndClientId(String accountId, String clientId);
    Account findAccountByAccountId(String accountId);
    @Transactional
    void deleteAccountByAccountId(String accountId);
}
