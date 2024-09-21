package com.prakhar.client.event;

import org.springframework.context.ApplicationEvent;

import com.prakhar.client.entity.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{


    private User user;
    //url we have to create for a user when we send email to click on it
    private String applicationUrl;

    public RegistrationCompleteEvent(User user,String applicationUrl) {
        super(user);
        
        this.user = user;
        this.applicationUrl = applicationUrl;


    }
    

}
