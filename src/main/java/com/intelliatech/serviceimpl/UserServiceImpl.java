package com.intelliatech.serviceimpl;

import com.intelliatech.dao.UserDao;
import com.intelliatech.dtos.UserDto;
import com.intelliatech.models.User;
import com.intelliatech.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //Slf4j logger  implement
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        log.info("inside UserService mehthod-->addUser");

        //Create object for userDto to user entity
        User user = new User();
        UserDto userDto1 = new UserDto();

        //Let change user password into the encrypted form

           userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));

        //copy userDto in the user entity
        BeanUtils.copyProperties(userDto,user);

        log.info("sent user object to dao ...");
        BeanUtils.copyProperties(this.userDao.save(user),userDto1);

        log.info("return from the user service-->>addUser method");
        return userDto1;
    }

    @Override
    public UserDto getUser(int id) {
        log.info("inside the  user service --> getUser method");


        UserDto userDto = new UserDto();

        log.info("send user id to the User dao for fetch the full user by id");
        BeanUtils.copyProperties(this.userDao.findByUserId(id),userDto);

        log.info("return from the user service --> getUser method");
        return userDto;
    }
}
