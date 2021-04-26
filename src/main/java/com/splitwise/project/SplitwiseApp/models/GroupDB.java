package com.splitwise.project.SplitwiseApp.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GroupDB {


    @Id
    @GeneratedValue
    private Long Id;

    private String groupName;

    private String userName;

    public Long getId() {
        return Id;
    }

    public GroupDB() {
    }

    public GroupDB(Long id, String groupName, String userName) {
        Id = id;
        this.groupName = groupName;
        this.userName = userName;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
