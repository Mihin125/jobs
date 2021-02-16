package com.job.controller;

import com.job.dto.UserSignUpDto;
import com.job.model.PendingUser;
import com.job.model.User;
import com.job.service.PendingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job/pending-user")
public class PendingUserController {
    @Autowired
    PendingUserService pendingUserService;

    @PostMapping("sign-up")
    public HttpStatus confirmSignUp(@RequestBody UserSignUpDto user){
        return pendingUserService.signUp(user);
    }
    @GetMapping("/{id}")
    public PendingUser findUserById(@PathVariable long id){
        return pendingUserService.findById(id);
    }

    @GetMapping()
    public List<PendingUser> findAll(@PathVariable long id){
        return pendingUserService.getAll();
    }
    @DeleteMapping("/{id}")
    public void deletePendingUser(@PathVariable long id){
        pendingUserService.deleteById(id);
    }



}
