package com.ifi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ifi.dao.AbstractDao;

public abstract class AbstractDaoImpl implements AbstractDao {

	/**
	 * Le contexte de persistance permettant l'accès aux données
	 */
	@PersistenceContext(unitName = "entityManager")
	EntityManager em;

	@Override
	public <T> T add(final T t) {
		this.em.persist(t);
		this.em.flush();

		return t;
	}

	@Override
	public <T> T update(final T t) {
		T res = null;
		res = this.em.merge(t);
		this.em.flush();
		return res;

	}
}
