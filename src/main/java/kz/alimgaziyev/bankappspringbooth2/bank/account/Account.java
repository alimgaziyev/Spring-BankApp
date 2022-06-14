package kz.alimgaziyev.bankappspringbooth2.bank.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String accountId;
    String clientId;
    @Builder.Default
    Double balance = 0.0;
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

    public Long getId() {
        return id;
    }

}
