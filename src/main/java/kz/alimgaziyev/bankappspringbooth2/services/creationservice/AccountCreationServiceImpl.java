package kz.alimgaziyev.bankappspringbooth2.services.creationservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.database.AccountDAO;
import kz.alimgaziyev.bankappspringbooth2.bank.account.AccountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationServiceImpl implements AccountCreationService {
    @Autowired
    AccountDAO accountDAO;
    @Override
    public void create(String accountId, String clientId, AccountType accountType) {
        try {
            Account account = Account.builder()
                                        .accountId(accountId)
                                        .clientId(clientId)
                                        .accountType(accountType)
                                        .isWithdrawAllowed(accountType.isWithdrawAllowed())
                                    .build();
            accountDAO.save(account);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
