package com.ifi.service;

import java.util.List;

import com.ifi.web.dto.DocumentDto;

public interface DocumentService {

	public DocumentDto addDocument(DocumentDto documentDto);

	public List<DocumentDto> getDocumentsByUser(String username);

	public void deleteDocument(String uuid);

}