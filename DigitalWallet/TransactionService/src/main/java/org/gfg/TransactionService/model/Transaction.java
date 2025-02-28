package org.gfg.TransactionService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String txnId;
    String senderId;
    String receiverId;
    double amount;
    String purpose;

    @Enumerated(EnumType.STRING)
    TxnStatus txnStatus;

    String txnStatusMessage;

    @CreationTimestamp
    Date createdOn;
    @UpdateTimestamp
    Date updatedOn;

    public Transaction(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public TxnStatus getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(TxnStatus txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getTxnStatusMessage() {
        return txnStatusMessage;
    }

    public void setTxnStatusMessage(String txnStatusMessage) {
        this.txnStatusMessage = txnStatusMessage;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
