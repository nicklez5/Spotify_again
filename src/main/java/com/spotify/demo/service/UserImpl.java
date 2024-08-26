package com.spotify.demo.service;

import com.spotify.demo.Repository.UserRepo;
import com.spotify.demo.entity.CurrentUserSession;
import com.spotify.demo.entity.LogIn;
import com.spotify.demo.entity.User;
import com.spotify.demo.Repository.SessionRepo;
import com.spotify.demo.exception.CurrentUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Override
    public User addUser(User user) {
        User user1 = userRepo.save(user);
        return user1;
    }

    @Override
    public User updateUser(User user) {
        User user1 = userRepo.save(user);
        return user1;
    }

    @Override
    public User readUser(Integer id) {
        Optional<User> user1 = userRepo.findById(id);
        return user1.get();
    }

    @Override
    public User deleteUser(Integer id) {
        Optional<User> user = userRepo.findById(id);
        User user1 = user.get();
        userRepo.delete(user1);
        return user1;
    }

    @Override
    public CurrentUserSession logIn(LogIn logIn) throws CurrentUserException {
        Optional<User> optionalUser=userRepo.findByEmail(logIn.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPassword().equals(logIn.getPassword())){
                Optional<CurrentUserSession> optionalSession= sessionRepo.findById(logIn.getEmail());
                if(optionalSession.isEmpty()){
                    String key = this.randomString();
                    CurrentUserSession session = new CurrentUserSession(logIn.getEmail(), user.getUserId(),key);
                    return sessionRepo.save(session);
                }else{
                    throw new CurrentUserException("User already present");
                }
            }else{
                throw new CurrentUserException("Wrong password");
            }
        }else{
            throw new CurrentUserException("Email is wrong");
        }

    }

    @Override
    public String logOut(String uuId) throws CurrentUserException{
        Optional<CurrentUserSession> optionalSession = sessionRepo.findByUuId(uuId);
        if(optionalSession.isPresent()){
            CurrentUserSession session = optionalSession.get();
            sessionRepo.delete(session);

            return "LogOut " + session.getEmail();
        }else{
            throw new CurrentUserException("Wrong UUID");
        }

    }

    private String randomString(){
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789@#$%&";
        int length = 6;
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            int index = random.nextInt(alphabets.length());
            str.append(alphabets.charAt(index));
        }
        return str.toString();
    }

}
