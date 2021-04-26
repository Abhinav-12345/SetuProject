package com.splitwise.project.SplitwiseApp.services;


import com.splitwise.project.SplitwiseApp.models.GroupDB;
import com.splitwise.project.SplitwiseApp.repository.GroupRepository;
import com.splitwise.project.SplitwiseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupNameService {

    @Autowired
    private GroupRepository groupRepository;
    public boolean isGroupExists(String groupName){

        if(groupRepository.findByGroupName(groupName)==null){
            return false;
        }
        else
            return true;
    }


    public List<GroupDB> getAllUsersOfAGroup(String groupName){

        return groupRepository.findByGroupName(groupName);
    }
}
