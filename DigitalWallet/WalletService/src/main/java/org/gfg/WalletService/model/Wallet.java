package org.gfg.WalletService.model;

import jakarta.persistence.*;
import org.common.model.enums.UserIdentifier;
import org.common.model.enums.WalletStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String userId;
    String name;
    String mobileNo;
    @Enumerated(value = EnumType.STRING)
    WalletStatus walletStatus;
    double balance;

    @Enumerated(value = EnumType.STRING)
    UserIdentifier userIdentifier;
    String userIdentifierValue;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;

    public Wallet(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public WalletStatus getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(WalletStatus walletStatus) {
        this.walletStatus = walletStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserIdentifier getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(UserIdentifier userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifierValue() {
        return userIdentifierValue;
    }

    public void setUserIdentifierValue(String userIdentifierValue) {
        this.userIdentifierValue = userIdentifierValue;
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
