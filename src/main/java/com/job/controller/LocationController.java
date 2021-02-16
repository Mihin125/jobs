package com.job.controller;


import com.job.dto.DistrictDto;
import com.job.model.District;
import com.job.service.DistrictService;
import com.job.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("94mart/location")
public class LocationController {
    @Autowired
    LocationService locationService;
    @Autowired
    DistrictService districtService;
    @GetMapping("/district/{districtId}")
    public District findDistrictById(@PathVariable long districtId){
        return locationService.findDistrictById(districtId);
    }
    @PostMapping("/admin/district/save")
    public void saveDistrict(@RequestBody DistrictDto districtDto){
        districtService.saveDistrict(districtDto);

    }
}
