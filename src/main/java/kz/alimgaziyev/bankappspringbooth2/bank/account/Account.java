package kz.alimgaziyev.bankappspringbooth2.bank.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "account_id")
    String accountId;
    @Column(name = "client_id")
    String clientId;
    @Builder.Default
    @Column(name = "balance")
    Double balance = 0.0;
    @Column(name = "account_type")
    AccountType accountType;
    @Column(name = "withdraw_allowed")
    Boolean isWithdrawAllowed;

    @Override
    public String toString() {
        return String.format("Account{accountId = %s, clientId = %s, balance = %.2f, type = %s}",
                accountId,
                clientId,
                balance,
                accountType);
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
