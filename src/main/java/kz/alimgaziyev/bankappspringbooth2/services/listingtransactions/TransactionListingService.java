package kz.alimgaziyev.bankappspringbooth2.services.listingtransactions;

import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionListingService {
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(String accountId);
}
