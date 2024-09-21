package com.prakhar.client.service;


import com.prakhar.client.entity.LoginRequest;
import com.prakhar.client.entity.User;
import com.prakhar.client.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    User findByEmail(String email);

    void createPasswordResetTokenForUser();

    void createPasswordResetTokenForUser(User user, String token);

    String verifyLogin(LoginRequest loginRequest);

}