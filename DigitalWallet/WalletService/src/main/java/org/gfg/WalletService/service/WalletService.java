package org.gfg.WalletService.service;

import org.common.constants.CommonConstants;
import org.common.model.enums.UserIdentifier;
import org.common.model.enums.WalletStatus;
import org.gfg.WalletService.model.Wallet;
import org.gfg.WalletService.repository.WalletRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Value("${wallet.initial.amount}")
    private String walletAmount;
    String txnStatus;
    String txnStatusMessage;

    public void createWalletAccount(String userId, String mobileNo, String name, String email,
                                    UserIdentifier userIdentifier, String userIdentifierValue){

        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setName(name);
        wallet.setMobileNo(mobileNo);
        wallet.setUserIdentifier(userIdentifier);
        wallet.setUserIdentifierValue(userIdentifierValue);
        wallet.setBalance(Double.parseDouble(walletAmount));
        wallet.setWalletStatus(WalletStatus.ACTIVE);


        walletRepository.save(wallet);

        System.out.println("Wallet account has been created");
    }


    public void updateWalletBalance(String txnId, String senderId, String receiverId, String amount){
        Wallet senderWallet = walletRepository.findByMobileNo(senderId);
        if (senderWallet==null){
            txnStatus = "FAILED";
            txnStatusMessage = "Sender waller doesn't exists";
        }
       if (senderWallet!=null && senderWallet.getWalletStatus()!=WalletStatus.ACTIVE){
           txnStatus = "FAILED";
           txnStatusMessage = "your wallet account is blocked";
       }
        Wallet receiverWallet = walletRepository.findByMobileNo(receiverId);
        if (receiverWallet==null){
            txnStatus = "FAILED";
            txnStatusMessage = "Receiver waller doesn't exists";
        }
        if (receiverWallet!=null && receiverWallet.getWalletStatus()!=WalletStatus.ACTIVE){
            txnStatus = "FAILED";
            txnStatusMessage = "Receiver wallet account is blocked";
        }

        if (senderWallet.getBalance()<Double.parseDouble(amount)){
            txnStatus = "FAILED";
            txnStatusMessage = "Insufficient balance";
        }

       else if (doTransaction(senderId,receiverId,Double.parseDouble(amount))){
            txnStatus = "SUCCESS";
            txnStatusMessage = "Transaction is successfull";
        }else {
            txnStatus = "PENDING";
            txnStatusMessage = "Transaction is pending";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TXN_ID,txnId);
        jsonObject.put(CommonConstants.TXN_STATUS,txnStatus);
        jsonObject.put(CommonConstants.TXN_STATUS_MESSAGE,txnStatusMessage);

        kafkaTemplate.send("TXN_UPDATE_TOPIC",jsonObject.toString());

        System.out.println("Transaction updated details send to kafka");

    }


    @Transactional
    public boolean doTransaction(String sender,String receiver, double amount){
        boolean isDone = false;
        try {
            walletRepository.updateWallet(sender,-amount);
            walletRepository.updateWallet(receiver,amount);
            isDone = true;
        }
        catch (Exception exception){
            isDone = false;
            System.out.println(exception);
        }
        return isDone;
    }


}
