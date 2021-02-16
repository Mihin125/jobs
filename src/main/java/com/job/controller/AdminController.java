package com.job.controller;

import com.job.dto.RoleDto;
import com.job.service.OfferService;
import com.job.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("94mart/admin")
public class AdminController {

    @Autowired
    OfferService offerService;
    @Autowired
    UserRoleService userRoleService;

    @PutMapping("/accept-offer/{offerId}")
    public void acceptOffer(@PathVariable long offerId){
        offerService.acceptOffer(offerId);
    }
    @PutMapping("/reject-offer/{offerId}")
    public void rejectOffer(@PathVariable long offerId){
        offerService.rejectOffer(offerId);
    }
    @PostMapping("/roles")
    public void saveRole(@RequestBody RoleDto roleDto){userRoleService.save(roleDto);
    }

}
