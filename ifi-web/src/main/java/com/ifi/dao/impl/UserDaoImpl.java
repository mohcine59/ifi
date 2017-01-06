package com.ifi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ifi.dao.UserDao;
import com.ifi.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

	/**
	 * Le contexte de persistance permettant l'accès aux données
	 */
	@PersistenceContext(unitName = "entityManager")
	EntityManager em;

	@Override
	public User getUser(String username) {
		return this.em.find(User.class, username);
	}

	@Override
	public User login(String username, String pwd) {
		User user = this.em.find(User.class, username);
		if(user.getPassword().equals(pwd)){
			return user;
		}
		return null;
	}

}
