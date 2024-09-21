package com.prakhar.client.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.prakhar.client.entity.User;
import com.prakhar.client.event.RegistrationCompleteEvent;
import com.prakhar.client.service.UserService;

import lombok.extern.slf4j.Slf4j;

//This class listens for the RegistrationCOmpleteEvent
//and onApplicationEvent() is invoked when event is triggered

//to print logger
@Component
@Slf4j
public class RegistrationCompleteEventListner implements ApplicationListener<RegistrationCompleteEvent>{

    @Autowired
    private UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
    // create the verification token for the user and attach to  link
    //link created can send mail to user, user click and re direct to application

    User user = event.getUser();
    //and this token we will store in DB to match with user token
    String token = UUID.randomUUID().toString();
    //Save user, token to db
    userService.saveVerificationTokenForUser(token,user);
        //send mail to user
        String url = event.getApplicationUrl()+"/verifyRegistration?token="+token;
        //mimiciking this is email
        log.info("Click the link to verify your account: {}", url);

    }

}
