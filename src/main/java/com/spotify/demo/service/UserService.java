package com.spotify.demo.service;

import com.spotify.demo.entity.CurrentUserSession;
import com.spotify.demo.entity.LogIn;
import com.spotify.demo.entity.User;
import com.spotify.demo.exception.CurrentUserException;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public User updateUser(User user);
    public User readUser(Integer id);
    public User deleteUser(Integer id);
    public CurrentUserSession logIn(LogIn login) throws CurrentUserException;

    public String logOut(String uuId) throws CurrentUserException;


}
