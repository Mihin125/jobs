package com.job.service;

import com.job.dto.UserSignUpDto;
import com.job.model.PendingUser;
import com.job.model.User;
import com.job.repository.PendingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PendingUserService {
    @Autowired
    PendingUserRepository pendingUserRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    BlackListService blackListService;
    @Autowired
    OfferService offerService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;

    public List<PendingUser> getAll(){
        return pendingUserRepository.findAll();
    }
    public PendingUser findByCompanyName(String name){
        return pendingUserRepository.findByCompanyName(name);
    }
    public PendingUser findById(long id){
        return pendingUserRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public HttpStatus signUp(UserSignUpDto userDto){
        User existingUser = userService.findByEmail(userDto.getEmail());
        if(blackListService.findByEmail(userDto.getEmail())!=null)return HttpStatus.FORBIDDEN;
        if (existingUser!=null)return HttpStatus.BAD_REQUEST;
        if(userDto.getCompanyName()!=null &&
                userDto.getDistrict()!=null&&
                userDto.getContactNumber()!=null &&
                userDto.getPassword()!= null ) {

            PendingUser user=new PendingUser();
            user.setCompanyName(userDto.getCompanyName());
            user.setDistrict(userDto.getDistrict());
            user.setPassword(userDto.getPassword());
            user.setContactNumber(userDto.getContactNumber());
            user.setEmail(userDto.getEmail());
            if (pendingUserRepository.save(user) != null) {
                return HttpStatus.OK;
            }
        }return HttpStatus.BAD_REQUEST;

    }
    public void deleteById(long id){
        pendingUserRepository.deleteById(id);
    }
}
