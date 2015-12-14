package com.ifi.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ifi.service.DocumentService;
import com.ifi.web.Tools;
import com.ifi.web.dto.DocumentDto;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Transactional
	@RequestMapping(value = "/documents", method = RequestMethod.GET)
	public String getDocuments(final Model model, final Principal principal) {

		final List<DocumentDto> listDocuments = this.documentService.getDocumentsByUser(principal.getName());

		model.addAttribute("listDocuments", listDocuments);
		model.addAttribute("username", principal.getName());

		return "documents";
	}

	@Transactional
	@RequestMapping(value = "/document/add", method = RequestMethod.POST)
	public String addDocument(@RequestParam("file") final MultipartFile doc, final Principal principal) {

		final DocumentDto dto = new DocumentDto();
		dto.setFilename(doc.getOriginalFilename());
		dto.setUsername(principal.getName());

		this.documentService.addDocument(dto);

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		try {
			// map.add("file", new ByteArrayResource(doc.getBytes()));
			// map.add("filename", doc.getOriginalFilename());
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.setContentDispositionFormData("file", doc.getOriginalFilename());

			// final HttpEntity<LinkedMultiValueMap<String, Object>>
			// requestEntity = new HttpEntity<LinkedMultiValueMap<String,
			// Object>>(
			// map, headers);

			final File tempFile = File.createTempFile("xyz", "");

			FileCopyUtils.copy(doc.getInputStream(), new FileOutputStream(tempFile));

			map.add("file", new FileSystemResource(tempFile));

			final HttpEntity<Object> entity = new HttpEntity<Object>(map, headers);

			final ResponseEntity<String> out = restTemplate.exchange("http://localhost:9191/documents/" + dto.getUuid(),
					HttpMethod.POST, entity, String.class);
			// restTemplate.postForObject("http://localhost:9191/documents/" +
			// dto.getUuid(), map, String.class);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return "redirect:/documents";
	}

	@Transactional
	@RequestMapping(value = "/document/delete/{uuid}", method = RequestMethod.GET)
	public String deleteDocument(@PathVariable final String uuid, final Principal principal) {
		this.documentService.deleteDocument(uuid);

		final RestTemplate restTemplate = Tools.getClientRest();
		final ResponseEntity<String> out = restTemplate.exchange("http://localhost:9191/documents/" + uuid,
				HttpMethod.DELETE, null, String.class);

		return "redirect:/documents";
	}
}