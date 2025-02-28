package org.gfg.OnboardingService.controller;

import jodd.util.StringUtil;
import org.gfg.OnboardingService.model.User;
import org.gfg.OnboardingService.request.UserCreationRequest;
import org.gfg.OnboardingService.response.UserCreationResponse;
import org.gfg.OnboardingService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboarding-service")
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/onboard/user")
    public ResponseEntity<UserCreationResponse> onboardUser(@RequestBody UserCreationRequest userCreationRequest){
        UserCreationResponse userCreationResponse = new UserCreationResponse();
        if (userCreationRequest==null){
            userCreationResponse.setMessage("Request Cannot be null");
            userCreationResponse.setCode("01");
            return new ResponseEntity<>(userCreationResponse, HttpStatus.BAD_REQUEST);
        }
        if (StringUtil.isBlank(userCreationRequest.getEmail()) || StringUtil.isBlank(userCreationRequest.getName())){
            userCreationResponse.setMessage("Email or Name cannot be blank");
            userCreationResponse.setCode("02");
            return new ResponseEntity<>(userCreationResponse, HttpStatus.BAD_REQUEST);
        }else if (StringUtil.isBlank(userCreationRequest.getUserIdentifierValue()) || StringUtil.isBlank(userCreationRequest.getUserIdentifierValue())){
            userCreationResponse.setMessage("Please provide the user identification");
            userCreationResponse.setCode("03");
            return new ResponseEntity<>(userCreationResponse, HttpStatus.BAD_REQUEST);
        }else if (StringUtil.isBlank(userCreationRequest.getMobileNo()) ){
            userCreationResponse.setMessage("Mobile No cannot be blank");
            userCreationResponse.setCode("04");
            return new ResponseEntity<>(userCreationResponse, HttpStatus.BAD_REQUEST);
        }else if (StringUtil.isNotBlank(userCreationRequest.getMobileNo()) && userCreationRequest.getMobileNo().length()!=10){
            userCreationResponse.setMessage("Please provide correct mobile No");
            userCreationResponse.setCode("05");
            return new ResponseEntity<>(userCreationResponse, HttpStatus.BAD_REQUEST);
        }

        User user = userService.onboardNewUser(userCreationRequest);

        if (user==null){
            userCreationResponse.setMessage("User Not Onboarded");
            return new ResponseEntity<>(userCreationResponse,HttpStatus.OK);
        }

        userCreationResponse.setMessage("User Has been onboarded successfully");
        userCreationResponse.setCode("00");
        userCreationResponse.setUserIdentifier(user.getUserIdentifier());
        userCreationResponse.setUserIdentifierValue(user.getUserIdentifierValue());
        userCreationResponse.setName(user.getName());
        userCreationResponse.setEmail(user.getEmail());

        return new ResponseEntity<>(userCreationResponse,HttpStatus.CREATED);
    }


    @GetMapping("/validate/user/{mobileNo}")
    public String validateUser(@PathVariable("mobileNo") String mobileNo){
        return userService.getUserData(mobileNo);
    }


}
