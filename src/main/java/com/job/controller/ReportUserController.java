package com.job.controller;

import com.job.model.RedList;
import com.job.service.RedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("94mart/red-list")
public class ReportUserController {
    @Autowired
    RedListService redListService;

    @GetMapping("/admin")
    public List<RedList> getAll(){
        return redListService.getAll();
    }

    @DeleteMapping("/admin/delete/{userId}")
    public void deleteRedListUser(@PathVariable long userId){
        redListService.deleteRedListUser(userId);
    }



}
