package com.ifi.dao;

public interface AbstractDao {

	public <T> T add(final T t);

	public <T> T update(final T t);
}
