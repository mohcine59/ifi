package com.ifi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ifi.dao.TweetDao;
import com.ifi.entity.Tweet;

@Repository("tweetDao")
public class TweetDaoImpl extends AbstractDaoImpl implements TweetDao {

	/**
	 * Le contexte de persistance permettant l'accès aux données
	 */
	@PersistenceContext(unitName = "entityManager")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> getLastTweets() {
		final String queryString = "SELECT t FROM Tweet t ORDER BY t.date DESC";

		final Query query = this.em.createQuery(queryString);
		query.setMaxResults(5);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> getTweetsByHastag(final String hashTag) {
		final String queryString = "SELECT t FROM Tweet t WHERE t.tweet like :tag ORDER BY t.date DESC";

		final Query query = this.em.createQuery(queryString);
		query.setParameter("tag", "%#" + hashTag + "%");
		query.setMaxResults(10);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> getTweetsByUsername(final String username) {
		final String queryString = "SELECT t FROM Tweet t WHERE t.username = :username ORDER BY t.date DESC";

		final Query query = this.em.createQuery(queryString);
		query.setParameter("username", username);
		query.setMaxResults(5);

		return query.getResultList();
	}
}