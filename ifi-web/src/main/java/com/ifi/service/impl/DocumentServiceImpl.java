package com.ifi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifi.dao.DocumentDao;
import com.ifi.entity.Document;
import com.ifi.service.DocumentService;
import com.ifi.web.dto.DocumentDto;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentDao documentDao;

	@Override
	public DocumentDto addDocument(final DocumentDto documentDto) {
		final Document doc = new ModelMapper().map(documentDto, Document.class);

		return new ModelMapper().map(this.documentDao.add(doc), DocumentDto.class);
	}

	@Override
	public void deleteDocument(final String uuid) {
		this.documentDao.deleteDocument(uuid);
	}

	@Override
	public List<DocumentDto> getDocumentsByUser(final String username) {
		final List<Document> documents = this.documentDao.getDocumentsByUser(username);
		if ((documents != null) && (documents.size() > 0)) {
			final List<DocumentDto> listDocuments = new ArrayList<>(documents.size());
			for (final Document document : documents) {
				listDocuments.add(new ModelMapper().map(document, DocumentDto.class));
			}

			return listDocuments;
		}
		return null;
	}

}