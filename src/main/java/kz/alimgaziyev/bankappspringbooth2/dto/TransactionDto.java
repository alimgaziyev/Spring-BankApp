package kz.alimgaziyev.bankappspringbooth2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionDto {
    String accountId;
    String transactionType;
    double amount;
    String date;
    boolean isTransferred;
}
