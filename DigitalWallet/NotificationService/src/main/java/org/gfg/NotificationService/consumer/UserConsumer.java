package org.gfg.NotificationService.consumer;

import org.common.constants.CommonConstants;
import org.common.model.enums.UserIdentifier;
import org.gfg.NotificationService.worker.EmailWorker;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @Autowired
    EmailWorker emailWorker;

    @KafkaListener(topics = "USER_DETAILS_QUEUE", groupId = "user-consumer")
    public void consumeNewlyCreatedUser(String data){
        System.out.println("User Data Received: "+data);
        JSONObject jsonObject = new JSONObject(data);
        String name = jsonObject.getString(CommonConstants.USER_NAME);
        String email = jsonObject.getString(CommonConstants.USER_EMAIL);
        UserIdentifier userIdentifier = jsonObject.getEnum(UserIdentifier.class,CommonConstants.USER_IDENTIFIER);
        String userIdentifierValue = jsonObject.getString(CommonConstants.USER_IDENTIFIER_VALUE);

        emailWorker.sendEmailNotification(name,email,userIdentifier,userIdentifierValue);
    }
}
