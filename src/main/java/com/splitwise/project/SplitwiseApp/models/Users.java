package com.splitwise.project.SplitwiseApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
public class Users{

    @Id
    private String userName;

    private Long phoneNumber;

    private String email;

    public Users(String userName, Long phoneNumber, String email) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Users() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
