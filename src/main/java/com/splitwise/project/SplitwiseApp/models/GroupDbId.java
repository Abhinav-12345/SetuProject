package com.splitwise.project.SplitwiseApp.models;


import java.io.Serializable;

public class GroupDbId implements Serializable {

    private String groupName;

    public GroupDbId() {
    }

    private String userName;

    public GroupDbId(String groupName, String userName) {
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
