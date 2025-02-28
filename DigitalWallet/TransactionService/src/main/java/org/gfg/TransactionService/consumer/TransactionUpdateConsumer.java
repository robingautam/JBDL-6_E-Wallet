package org.gfg.TransactionService.consumer;

import org.common.constants.CommonConstants;
import org.gfg.TransactionService.model.TxnStatus;
import org.gfg.TransactionService.repository.TransactionRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionUpdateConsumer {


    @Autowired
    TransactionRepository transactionRepository;


    @KafkaListener(topics = "TXN_UPDATE_TOPIC", groupId = "txn-update-group")
    public void consumeUpdatedTransactions(String data){
        System.out.println("Updated Transaction: "+data);
        JSONObject jsonObject = new JSONObject(data);
        String txnId = jsonObject.getString(CommonConstants.TXN_ID);
        String txnStatus = jsonObject.getString(CommonConstants.TXN_STATUS);
        String txnStatusMessage = jsonObject.getString(CommonConstants.TXN_STATUS_MESSAGE);

        transactionRepository.updateTransactionDetails(txnId, TxnStatus.valueOf(txnStatus),txnStatusMessage);

        System.out.println("Transaction details updated");

    }
}
