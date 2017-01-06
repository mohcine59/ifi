package com.ifi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifi.dao.UserDao;
import com.ifi.entity.User;
import com.ifi.errors.UserAlreadyExistsException;
import com.ifi.errors.UserNotFoundException;
import com.ifi.service.UserService;
import com.ifi.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDto addUser(UserDto userDto) throws UserAlreadyExistsException, Exception {
		User user = new ModelMapper().map(userDto, User.class);
		user = this.userDao.add(user);
		return new ModelMapper().map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String username) throws Exception {
		User user = this.userDao.getUser(username);
		
		if(user != null){
			return new ModelMapper().map(user, UserDto.class);
		}
		return null;
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws UserNotFoundException, Exception {
		User user = new ModelMapper().map(userDto, User.class);
		user = this.userDao.update(user);
		
		return new ModelMapper().map(user, UserDto.class);
	}

	@Override
	public void deleteUser(String username) {
		
	}

	@Override
	public UserDto login(String username, String pwd) {
		User user = this.userDao.login(username, pwd);
		return new ModelMapper().map(user, UserDto.class);
	}

}
