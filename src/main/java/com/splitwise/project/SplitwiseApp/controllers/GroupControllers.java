package com.splitwise.project.SplitwiseApp.controllers;


import com.splitwise.project.SplitwiseApp.dto.GroupDTO;
import com.splitwise.project.SplitwiseApp.models.GroupDB;
import com.splitwise.project.SplitwiseApp.repository.GroupRepository;
import com.splitwise.project.SplitwiseApp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupControllers {


    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserServiceImpl userService;

     @PostMapping("/add")
     public String addGroupAndMembers(@RequestBody GroupDTO groupDTO){

         GroupDB group = new GroupDB();

         if(!userService.isUserAccountExists(groupDTO.getUserName())){
             return "Please register the Members Account: "+group.getUserName();
         }

         group.setGroupName(groupDTO.getGroupName());
         group.setUserName(groupDTO.getUserName());
         groupRepository.save(group);
         return "";
     }

         @GetMapping("{groupName}")
         public List<GroupDB> addGroup(@PathVariable("groupName") String groupName){
             return groupRepository.findByGroupName(groupName);
         }


}
