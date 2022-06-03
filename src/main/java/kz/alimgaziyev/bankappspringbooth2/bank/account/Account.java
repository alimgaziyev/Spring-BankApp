package kz.alimgaziyev.bankappspringbooth2.bank.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue
    Long id;
    String accountId;
    String clientId;
    @Builder.Default
    Double balance = 0.0;
    AccountType accountType;
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
