package com.job.repository;

import com.job.model.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PendingUserRepository extends JpaRepository<PendingUser,Long> {

    @Query(value = "select * from pending_user where company_name=?",nativeQuery = true)
    PendingUser findByCompanyName(String companyName);
}
