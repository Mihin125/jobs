package com.job.controller;

import com.job.dto.UserSignUpDto;
import com.job.model.User;
import com.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("94mart/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public HttpStatus signUp(@RequestBody UserSignUpDto user){
        return userService.signUp(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id){
        return userService.findById(id);
    }

    @PutMapping("/update/{userId}")
    public HttpStatus updateUser(@PathVariable long userId,@RequestBody UserSignUpDto updateUserDto){
        return userService.updateUser(userId,updateUserDto);
    }
    @DeleteMapping("/admin/ban-user/{userId}")
    public void banUser(@PathVariable long userId){
        userService.banUser(userId);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }
}
