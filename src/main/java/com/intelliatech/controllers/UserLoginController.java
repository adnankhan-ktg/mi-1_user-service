package com.intelliatech.controllers;

import com.intelliatech.dao.UserDao;
import com.intelliatech.dtos.Token;
import com.intelliatech.dtos.UserDto;
import com.intelliatech.dtos.UserLogin;
import com.intelliatech.models.User;
import com.intelliatech.spring_security.JwtTokenUtil;
import com.intelliatech.spring_security.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserLoginController {

    private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin)
    {
        log.info("inside the UserLoginController --> login method");

        log.info("object(username) send to the dao layer for check user exists or not");
        //check user exists or not
        User user1 = this.userDao.findByUsername(userLogin.getUsername());

//        if(this.userDao.getUserStatusByUsername(userLogin.getUsername()) != 0)
        if(user1 != null)
        {
            log.info("Given User exits check password for him");

            log.info("object again send to the dao layer -->userDao");
//           String password =  this.userDao.getPasswordOfUsername(userLogin.getPassword());

            //raw and encrypted password compare
            if(passwordEncoder.matches(userLogin.getPassword(),user1.getPassword()))
            {
                log.info("username and password matched");
                //Generate JWT Token....

                final UserDetails userDetails  = this.jwtUserDetailsService.loadUserByUsername(userLogin.getUsername());
                String token = this.jwtTokenUtil.generateToken(userDetails);
                Token token_1 = new Token(token);
                log.info("return jwt token..successfully");
                return ResponseEntity.status(HttpStatus.OK).body(token_1);



            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        }
        else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




    }

}
