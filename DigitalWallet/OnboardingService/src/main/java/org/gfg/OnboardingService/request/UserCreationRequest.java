package org.gfg.OnboardingService.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.model.enums.UserIdentifier;
import org.common.model.enums.UserStatus;
import org.common.model.enums.UserType;
import org.gfg.OnboardingService.model.User;


public class UserCreationRequest {

    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private String dob;

    private UserIdentifier userIdentifier;
    private String userIdentifierValue;
    private  UserType userType;

    public UserCreationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public User toUser(){
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setDob(this.dob);
        user.setUserIdentifierValue(this.userIdentifierValue);
        user.setUserIdentifier(this.userIdentifier);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setMobileNo(this.mobileNo);
        return user;
    }
}
