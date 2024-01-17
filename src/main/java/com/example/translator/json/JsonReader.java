package com.example.translator.json;

import com.example.translator.entity.Language;
import com.example.translator.entity.TranslateResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public String getLanguageFromFile() throws FileNotFoundException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        String jsonPath = "./src/main/resources/languages.json";
        File jsonFile = new File(jsonPath);
        try {
            Scanner jsonScanner = new Scanner(jsonFile);
            while (jsonScanner.hasNextLine()) {
                jsonStringBuilder.append(jsonScanner.nextLine()).append("\n");
            }
            return jsonStringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
