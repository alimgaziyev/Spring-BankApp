package kz.alimgaziyev.bankappspringbooth2.dto;

import kz.alimgaziyev.bankappspringbooth2.bank.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class AccountDto {
    Integer clientId;
    String accountType;

    public String getClientId() {
        return clientId.toString();
    }

    public AccountType getAccountType() {
        AccountType ans = switch (this.accountType) {
            case "FIXED"    -> AccountType.FIXED;
            case "SAVING"   -> AccountType.SAVING;
            case "CHECKING" -> AccountType.CHECKING;
            default -> null;
        };
        return ans;
    }

    @Override
    public String toString() {
        return String.format("%d %s", clientId, accountType);
    }
}
