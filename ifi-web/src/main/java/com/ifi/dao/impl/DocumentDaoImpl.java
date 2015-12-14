package com.ifi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ifi.dao.DocumentDao;
import com.ifi.entity.Document;

@Repository("documentDao")
public class DocumentDaoImpl extends AbstractDaoImpl implements DocumentDao {

	/**
	 * Le contexte de persistance permettant l'accès aux données
	 */
	@PersistenceContext(unitName = "entityManager")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getDocumentsByUser(final String username) {
		final String queryString = "SELECT d FROM Document d where d.username = :username ORDER BY d.filename";

		final Query query = this.em.createQuery(queryString);
		query.setParameter("username", username);

		return query.getResultList();

	}

	@Override
	public void deleteDocument(final String uuid) {
		final String queryString = "delete FROM Document WHERE uuid = :uuid";
		final Query query = this.em.createQuery(queryString);
		query.setParameter("uuid", uuid);

		query.executeUpdate();
	}
}