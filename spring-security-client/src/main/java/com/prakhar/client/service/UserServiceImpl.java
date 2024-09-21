package com.prakhar.client.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prakhar.client.entity.LoginRequest;
import com.prakhar.client.entity.PasswordResetToken;
import com.prakhar.client.entity.User;
import com.prakhar.client.entity.VerificationToken;
import com.prakhar.client.model.UserModel;
import com.prakhar.client.repository.PasswordRestTokenRepository;
import com.prakhar.client.repository.UserRepository;
import com.prakhar.client.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordRestTokenRepository passwordRestTokenRepository;

    @Override
    public User registerUser(UserModel userModel) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("Seller");
        // here getting plane password that user is putting
        // but putting it to DB we have to encriypt it
        // for that create password encoder in config package
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);

    }

    @Override
    public String validateVerificationToken(String token) {
        // TODO Auto-generated method stub
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExipirationTime().getTime() - cal.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        userRepository.save(user);

        return "valid";

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser() {

    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordRestTokenRepository.save(passwordResetToken);
    }

    @Override
    public String verifyLogin(LoginRequest loginRequest) {
        // if(userRepository.findByEmail(loginRequest.getEmail())&&userRepository.findBy)
        User user = findByEmail(loginRequest.getEmail());

        if (user != null) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return "Login Sucess";
            } else {
                return "Bad User";
            }
        } else {
            return "Bad User";
        }
    }

}
