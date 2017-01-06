package com.ifi.dao;

import com.ifi.entity.User;

public interface UserDao extends AbstractDao {

	User getUser(String username);

	User login(String username, String pwd);
}
