package com.job.repository;

import com.job.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    @Query(value = "select * from  district where district_name =?",nativeQuery = true)
    District findDistinctByDistrictName(String districtName);
}
