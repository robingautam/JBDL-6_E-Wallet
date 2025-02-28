package org.gfg.TransactionService.services;

import org.common.constants.CommonConstants;
import org.gfg.TransactionService.model.Transaction;
import org.gfg.TransactionService.model.TxnStatus;
import org.gfg.TransactionService.repository.TransactionRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {


    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    public String initiateTransaction(String sender, String receiver,String amount,String purpose){

        Transaction transaction = new Transaction();
        String txnId = UUID.randomUUID().toString();
        transaction.setTxnId(txnId);
        transaction.setSenderId(sender);
        transaction.setReceiverId(receiver);
        transaction.setPurpose(purpose);
        transaction.setAmount(Double.parseDouble(amount));
        transaction.setTxnStatus(TxnStatus.INITIATED);
        transaction.setTxnStatusMessage("Transaction initiated");

        transactionRepository.save(transaction);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.SENDER_ID,sender);
        jsonObject.put(CommonConstants.RECEIVER_ID,receiver);
        jsonObject.put(CommonConstants.TXN_AMOUNT,amount);
        jsonObject.put(CommonConstants.TXN_ID,txnId);

        kafkaTemplate.send(CommonConstants.TXN_DETAILS_TOPIC,jsonObject.toString());

        return txnId;

    }
}
