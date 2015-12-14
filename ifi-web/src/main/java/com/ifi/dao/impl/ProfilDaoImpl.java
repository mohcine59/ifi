package com.ifi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ifi.dao.ProfilDao;
import com.ifi.entity.Profile;

@Repository("profilDao")
public class ProfilDaoImpl extends AbstractDaoImpl implements ProfilDao {

	/**
	 * Le contexte de persistance permettant l'accès aux données
	 */
	@PersistenceContext(unitName = "entityManager")
	EntityManager em;

	@Override
	public Profile getProfil(final Profile profil) {
		return this.em.find(Profile.class, profil.getUsername());
	}

}