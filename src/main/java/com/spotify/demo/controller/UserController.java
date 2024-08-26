package com.spotify.demo.controller;

import com.spotify.demo.entity.CurrentUserSession;
import com.spotify.demo.entity.LogIn;
import com.spotify.demo.entity.User;
import com.spotify.demo.exception.CurrentUserException;
import com.spotify.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return new ResponseEntity<User>(user1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        User user1 = userService.deleteUser(id);
        return new ResponseEntity<User>(user1,HttpStatus.OK);

    }
    @GetMapping("/read/{id}")
    public ResponseEntity<User> readUser(@PathVariable("id") Integer id) {
        User user1 = userService.readUser(id);
        return new ResponseEntity<User>(user1,HttpStatus.OK);

    }
    @PostMapping("/login")
    public ResponseEntity<CurrentUserSession> logIn(@RequestBody LogIn logIn) throws CurrentUserException {
        CurrentUserSession session = userService.logIn(logIn);
        return new ResponseEntity<CurrentUserSession>(session, HttpStatus.OK);
    }
    @DeleteMapping("/logOut/{uuId}")
    public ResponseEntity<String> logOut(@PathVariable("uuId") String uuId) throws CurrentUserException {
        String message = userService.logOut(uuId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

}
