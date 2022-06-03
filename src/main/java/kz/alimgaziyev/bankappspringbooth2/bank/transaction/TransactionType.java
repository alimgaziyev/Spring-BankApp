package kz.alimgaziyev.bankappspringbooth2.bank.transaction;

import javax.persistence.Embeddable;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
