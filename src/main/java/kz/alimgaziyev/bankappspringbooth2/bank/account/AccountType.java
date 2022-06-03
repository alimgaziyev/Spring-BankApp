package kz.alimgaziyev.bankappspringbooth2.bank.account;
public enum AccountType {
    CHECKING("CHECKING"),
    SAVING("SAVING"),
    FIXED("FIXED");

    private String name;
    AccountType(String checking) {
        name = checking;
    }
    public boolean isWithdrawAllowed() {
        return switch (this) {
            case CHECKING, SAVING -> true;
            case FIXED -> false;
        };
    }

    @Override
    public String toString() {
        return name;
    }
}