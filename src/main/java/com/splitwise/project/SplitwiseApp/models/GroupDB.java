package com.splitwise.project.SplitwiseApp.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@IdClass(GroupDbId.class)
public class GroupDB {

    @Id
    private String groupName;

    @Id
    private String userName;


    public GroupDB() {
    }

    public GroupDB( String groupName, String userName) {

        this.groupName = groupName;
        this.userName = userName;
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
