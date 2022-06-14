package kz.alimgaziyev.bankappspringbooth2.services.transferservice;

import kz.alimgaziyev.bankappspringbooth2.bank.account.Account;
import kz.alimgaziyev.bankappspringbooth2.services.depositservice.AccountDepositService;
import kz.alimgaziyev.bankappspringbooth2.services.listingservice.AccountListingService;
import kz.alimgaziyev.bankappspringbooth2.services.withdrawservice.AccountWithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{
    private final AccountWithdrawService accountWithdrawService;
    private final AccountListingService accountListingService;
    private final AccountDepositService accountDepositService;

    @Override
    public void transferFromOneToAnotherAccount(String clientId, String fromAccountId, String toAccountId, Double amount) {
        accountWithdrawService.withdraw(fromAccountId, clientId, amount);
        Account account = accountListingService.getAccount(toAccountId);
        accountDepositService.deposit(toAccountId, account.getClientId(), amount);
    }
}
