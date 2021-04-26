package com.splitwise.project.SplitwiseApp.controllers;


import com.splitwise.project.SplitwiseApp.dto.UsersDTO;
import com.splitwise.project.SplitwiseApp.models.Users;
import com.splitwise.project.SplitwiseApp.repository.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add")
    public String signUpUser(@RequestBody UsersDTO usersDTO){

        Users users = new Users();

        users.setUserName(usersDTO.getUserName());
        users.setPhoneNumber(usersDTO.getPhoneNumber());
        users.setEmail(usersDTO.getEmail());

        userRepository.save(users);

        return "User created";

    }

    @GetMapping("{userName}")
    public  Users getUsers(@PathVariable("userName") String userName){

        return userRepository.findByUserName(userName);
    }
}
