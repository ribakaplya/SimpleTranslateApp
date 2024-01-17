package com.example.translator.rest;

import com.example.translator.entity.TranslateData;
import com.example.translator.json.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {
    @Value("${translate.token}")
    private String token;
    private final String url = "https://translate.api.cloud.yandex.net/translate/v2";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    JsonReader jsonReader;

    public String translate(TranslateData translateData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(translateData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url + "/translate", request, String.class);

        return jsonReader.getTranslate(responseEntity.getBody());
    }
}
