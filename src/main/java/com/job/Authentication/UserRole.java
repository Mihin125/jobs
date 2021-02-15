package com.job.Authentication;

import com.job.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static com.job.Authentication.UserPermission.*;
@Entity
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
