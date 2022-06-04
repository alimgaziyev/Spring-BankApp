package kz.alimgaziyev.bankappspringbooth2.bank.transaction;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String accountId;
    TransactionType transactionType;
    Double amount;
    String date;
    boolean isTransferred;

    @Override
    public String toString() {
        String ans = switch (transactionType) {
            case DEPOSIT -> String.format("$%.2f | %s | to %s | at %s | isTransferred = %b", amount, transactionType, accountId, date, isTransferred);
            case WITHDRAW -> String.format("$%.2f | %s | from %s | at %s | isTransferred = %b", amount, transactionType, accountId, date, isTransferred);
        };
        return ans;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
