package com.example.translator.json;

import com.example.translator.entity.Language;
import com.example.translator.entity.TranslateResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonReader {
    public List<Language> getLanguage() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Language> languageList = new ArrayList<>();
        try {
            languageList = Arrays.asList(objectMapper.readValue(getLanguageFromFile(), Language[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return languageList;
    }

    public String getTranslate(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TranslateResult translateResult = objectMapper.readValue(text, TranslateResult.class);
            return translateResult.getTranslations().get(0).getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLanguageFromFile() {
        String jsonPath = "./src/main/resources/languages.json";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(jsonPath));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
