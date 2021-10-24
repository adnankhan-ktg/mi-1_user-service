package com.intelliatech.services;

import com.intelliatech.dtos.UserDto;

public interface UserService {

    UserDto addUser(UserDto userDto);
    UserDto getUser(int id);
}
