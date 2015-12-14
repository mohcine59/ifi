package com.ifi.dao;

import java.util.List;

import com.ifi.entity.Document;

public interface DocumentDao extends AbstractDao {

	public List<Document> getDocumentsByUser(String username);

	public void deleteDocument(String uuid);

}