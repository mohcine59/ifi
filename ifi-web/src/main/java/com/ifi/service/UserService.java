package com.ifi.service;

import com.ifi.entity.User;
import com.ifi.errors.UserAlreadyExistsException;
import com.ifi.errors.UserNotFoundException;
import com.ifi.web.dto.UserDto;

public interface UserService {

	UserDto addUser(UserDto u) throws UserAlreadyExistsException, Exception;

    UserDto getUser(String userName) throws Exception;

    UserDto updateUser(UserDto u) throws UserNotFoundException, Exception;

    void deleteUser(String username);
    
    UserDto login(String username, String pwd);
}
