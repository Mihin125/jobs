package com.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job.Authentication.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class PendingUser {
    @Id
    @GeneratedValue
    long id;
    private String companyName;
    @JsonIgnore
    private String password;
    private String district;
    private String contactNumber;
    private String email;
}
