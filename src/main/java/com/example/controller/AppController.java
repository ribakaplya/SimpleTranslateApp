package com.example.controller;

import com.example.database.model.RequestLogDto;
import com.example.database.repository.RequestLogRepository;
import com.example.integration.yandex.entity.Language;
import com.example.integration.yandex.entity.TextForTranslate;
import com.example.integration.yandex.entity.TranslateData;
import com.example.integration.yandex.json.JsonReader;
import com.example.integration.yandex.rest.YandexRestClient;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@RestController
public class AppController {
	private final JsonReader jsonReader;
	private final YandexRestClient yandexRestClient;

	private final RequestLogRepository requestLogRepository;

	public AppController(
			JsonReader jsonReader,
			YandexRestClient yandexRestClient,
			RequestLogRepository requestLogRepository
	) {
		this.jsonReader = jsonReader;
		this.yandexRestClient = yandexRestClient;
		this.requestLogRepository = requestLogRepository;
	}

	@GetMapping("/data")
	public List<RequestLogDto> data(Map<String, Object> model) throws SQLException {
		model.put("messages", requestLogRepository.getAll());
		return requestLogRepository.getAll();
	}

	@GetMapping
	public List<Language> main(Map<String, Object> model) {
		model.put("lang", jsonReader.getLanguage());
		return jsonReader.getLanguage();
	}

	@PostMapping
	public String translateText(
			@RequestBody() TextForTranslate textForTranslate,
			Map<String, Object> model
	) {
		String sourceLang = textForTranslate.sourceLang;
		String targetLang = textForTranslate.targetLang;

		TranslateData translateData = new TranslateData();  // ??
		translateData.setSourceLanguageCode(sourceLang);
		translateData.setTargetLanguageCode(targetLang);

		StringJoiner joiner = new StringJoiner(" ");
		String[] words = textForTranslate.text.trim().split(" ");

		RequestLogDto requestLogDto = new RequestLogDto();
		requestLogDto.setRequestId(getStringId());
		requestLogDto.setRequestLang(sourceLang);
		requestLogDto.setResponseLang(targetLang);

		for (String word : words) {
			Long id = getLongId();
			translateData.setTexts(Collections.singletonList(word));
			String translateWord = yandexRestClient.translate(translateData);

			requestLogDto.setId(id);
			requestLogDto.setDateTime(new Timestamp(System.currentTimeMillis()));
			requestLogDto.setRequest(word);
			requestLogDto.setResponse(translateWord);
			requestLogDto.setResponseIp("1.2.3.4");

			requestLogRepository.add(requestLogDto);
			joiner.add(translateWord);
		}

		model.put("result", joiner.toString());
		model.put("messages", textForTranslate);
		model.put("lang", jsonReader.getLanguage());

		return joiner.toString();
	}

	public static Long getLongId() {
		return UUID.randomUUID().getLeastSignificantBits();
	}

	public static String getStringId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
