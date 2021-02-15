package com.job.service;

import com.job.model.RedList;
import com.job.repository.RedListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedListService {
    @Autowired
    RedListRepository redListRepository;

    public void save(RedList redList){
        redListRepository.save(redList);
    }
    public List<RedList> getAll(){
        return redListRepository.findAll();
    }

    public void deleteRedListUser(long userId){
        redListRepository.deleteById(userId);
    }
}
