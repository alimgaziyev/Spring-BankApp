package kz.alimgaziyev.bankappspringbooth2.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDto {
    private String toAccountId;
    private Double amount;
}
