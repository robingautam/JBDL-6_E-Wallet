package org.gfg.OnboardingService.service;

import org.common.constants.CommonConstants;
import org.common.model.enums.UserType;
import org.gfg.OnboardingService.model.User;
import org.gfg.OnboardingService.repository.UserRepository;
import org.gfg.OnboardingService.request.UserCreationRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public User onboardNewUser(UserCreationRequest userCreationRequest){

        User user = userCreationRequest.toUser();
        String password = passwordEncoder.encode(userCreationRequest.getPassword());
        user.setPassword(password);
        user.setUserType(UserType.NORMAL);

        User savedUser = null;
        try {
            savedUser = userRepository.save(user);
           LOGGER.info("User Saved in the database");
        }
        catch (Exception ex){
            return null;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_NAME,savedUser.getName());
        jsonObject.put(CommonConstants.USER_EMAIL,savedUser.getEmail());
        jsonObject.put(CommonConstants.USER_ID,savedUser.getId());
        jsonObject.put(CommonConstants.USER_IDENTIFIER,savedUser.getUserIdentifier());
        jsonObject.put(CommonConstants.USER_IDENTIFIER_VALUE,savedUser.getUserIdentifierValue());
        jsonObject.put(CommonConstants.USER_MOBILE,savedUser.getMobileNo());

        kafkaTemplate.send(CommonConstants.USER_DETAILS_QUEUE_TOPIC,jsonObject.toString());

        return savedUser;
    }



    public String getUserData(String mobileNo){
        User user = userRepository.findByMobileNo(mobileNo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_MOBILE,user.getMobileNo());
        jsonObject.put(CommonConstants.USER_PASSWORD,user.getPassword());

        return jsonObject.toString();
    }
}
