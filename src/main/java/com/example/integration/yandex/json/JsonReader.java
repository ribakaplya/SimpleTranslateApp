package com.example.integration.yandex.json;

import com.example.exceptions.TranslatorException;
import com.example.integration.yandex.entity.Language;
import com.example.integration.yandex.entity.TranslateResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final ObjectMapper objectMapper;
	private final String path;
	private List<Language> languages;

	public JsonReader(
			ObjectMapper objectMapper,
			@Value("${translate.languages-path}") String path
	) {
		this.objectMapper = objectMapper;
		this.path = path;
	}

	@PostConstruct
	public void initLanguages() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String jsonString = new String(encoded, StandardCharsets.UTF_8);
		this.languages = objectMapper.readValue(jsonString, new TypeReference<>() {
		});
	}

	public List<Language> getLanguage() {
		return new ArrayList<>(languages);
	}

	public String getTranslate(String text) {
		try {
			TranslateResult translateResult = objectMapper.readValue(text, TranslateResult.class);
			return translateResult.getTranslations().get(0).getText();
		} catch (IOException e) {
			log.error("Translation error!", e);
			throw new TranslatorException(e);
		}
	}
}
