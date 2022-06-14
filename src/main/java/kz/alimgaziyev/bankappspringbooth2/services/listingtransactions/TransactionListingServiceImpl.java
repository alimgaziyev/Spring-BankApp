package kz.alimgaziyev.bankappspringbooth2.services.listingtransactions;

import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import kz.alimgaziyev.bankappspringbooth2.repository.TransactionRepo;
import kz.alimgaziyev.bankappspringbooth2.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionListingServiceImpl implements TransactionListingService {
    @Autowired
    TransactionRepo transactionRepo;
    @Override
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountIdAndClientId(String accountId, String clientId) {
        List<Transaction> transactions;
        ResponseEntity<List<TransactionDto>> ans;
        transactions = transactionRepo.findTransactionsByAccountIdAndClientId(accountId, clientId);
            if (transactions == null) {
                ans = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
            } else {
                List<TransactionDto> transactionsDto = new ArrayList<>();
                transactions.forEach(x -> transactionsDto.add(TransactionDto.builder()
                                                                    .accountId(x.getAccountId())
                                                                    .transactionType(x.getTransactionType()
                                                                    .toString())
                                                                    .amount(x.getAmount())
                                                                    .date(x.getDate())
                                                                    .isTransferred(x.isTransferred())
                                                            .build()));
                ans = ResponseEntity.status(HttpStatus.OK).body(transactionsDto);
            }
        return ans;
    }
}
