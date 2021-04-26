package com.splitwise.project.SplitwiseApp.services;

import com.splitwise.project.SplitwiseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {


    @Autowired
    private UserRepository userRepository;
    public boolean isUserAccountExists(String userName){

        if(userRepository.findByUserName(userName)==null){
            return false;
        }
        else
            return true;
    }
}
