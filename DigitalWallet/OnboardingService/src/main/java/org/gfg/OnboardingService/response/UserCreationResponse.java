package org.gfg.OnboardingService.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.model.enums.UserIdentifier;


public class UserCreationResponse {

    String message;
    String code;
    String name;
    String email;
    UserIdentifier userIdentifier;
    String userIdentifierValue;

    public UserCreationResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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


}
