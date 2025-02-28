package org.gfg.TransactionService.repository;

import org.gfg.TransactionService.model.Transaction;
import org.gfg.TransactionService.model.TxnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


    @Modifying
    @Transactional
    @Query("update transaction as t set t.txnStatus=:status,t.txnStatusMessage=:message where t.txnId=:txnId")
        // @Query(value = "update transaction set txn_status=:status and txn_status_message=:message where txn_id=:txnId",nativeQuery = true)
    void updateTransactionDetails(String txnId, TxnStatus status, String message);
}
