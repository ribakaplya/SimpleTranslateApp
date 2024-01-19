package com.example.integration.yandex.rest;

import com.example.exceptions.TranslatorException;
import com.example.integration.yandex.entity.TranslateData;
import com.example.integration.yandex.json.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class YandexRestClient {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String token;
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final JsonReader jsonReader;

	public final String folderId;
	public final Boolean speller;

	public YandexRestClient(
			@Qualifier("yandexRestTemplate") RestTemplate restTemplate,
			JsonReader jsonReader,
			ObjectMapper objectMapper,
			@Value("${translate.folder_id}") String folderId,
			@Value("${translate.speller}") Boolean speller,
			@Value("${translate.token}") String token
	) {
		this.restTemplate = restTemplate;
		this.jsonReader = jsonReader;
		this.objectMapper = objectMapper;
		this.token = token;
		this.folderId = folderId;
		this.speller = speller;
	}

	public String translate(TranslateData translateData) {
		translateData.setFolderId(folderId);
		translateData.setSpeller(speller);

		String jsonBody;
		try {
			jsonBody = objectMapper.writeValueAsString(translateData);
		} catch (JsonProcessingException e) {
			log.error("Failed to parse json!", e);
			throw new TranslatorException(e);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);

		HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/translate", request, String.class);

		return jsonReader.getTranslate(responseEntity.getBody());
	}
}
