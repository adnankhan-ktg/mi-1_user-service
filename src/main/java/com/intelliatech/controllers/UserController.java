package com.intelliatech.controllers;

import com.intelliatech.dtos.UserDto;
import com.intelliatech.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto)
    {
        log.info("inside UserController --> addUser");

        UserDto userDto1 = this.userService.addUser(userDto);
        if(userDto1 != null)
        {
            log.info("User successfully store in the database");
            return ResponseEntity.status(HttpStatus.OK).body(userDto1);
        }else{
            log.info("user not save in the database because of some issue");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
