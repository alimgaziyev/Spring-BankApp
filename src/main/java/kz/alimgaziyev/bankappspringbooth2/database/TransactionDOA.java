package kz.alimgaziyev.bankappspringbooth2.database;

import kz.alimgaziyev.bankappspringbooth2.bank.transaction.Transaction;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionDOA extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByAccountId(String accountId);
    @Transactional
    void deleteTransactionsByAccountId(String accountId);
}
