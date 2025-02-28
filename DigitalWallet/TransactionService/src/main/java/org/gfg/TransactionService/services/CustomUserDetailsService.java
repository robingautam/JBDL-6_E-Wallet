package org.gfg.TransactionService.services;

import org.common.constants.CommonConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     String response =  restTemplate.getForObject("http://localhost:8080/onboarding-service/validate/user/"+username, String.class);
     System.out.println("Response: "+response);

     if (response==null){
         System.out.println("Response from user service is null");
     }

        JSONObject jsonObject = new JSONObject(response);
        String user = jsonObject.getString(CommonConstants.USER_MOBILE);
        String password = jsonObject.getString(CommonConstants.USER_PASSWORD);

        UserDetails userDetails = User.builder().username(user).password(password).build();
        return userDetails;
    }
}
