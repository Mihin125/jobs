package com.job.service;

import com.job.model.BlackList;
import com.job.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {
    @Autowired
    BlackListRepository blackListRepository;

    public void save(BlackList blackList){
        blackListRepository.save(blackList);
    }
    public BlackList findByEmail(String email){
        return blackListRepository.findByEmail(email);
    }
}
