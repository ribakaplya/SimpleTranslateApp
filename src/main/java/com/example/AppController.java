package com.example;

import com.example.h2.GenId;
import com.example.h2.entity.Data;
import com.example.h2.service.DataService;
import com.example.translator.entity.Language;
import com.example.translator.entity.TextForTranslate;
import com.example.translator.entity.TranslateData;
import com.example.translator.entity.TranslateResult;
import com.example.translator.json.JsonReader;
import com.example.translator.rest.Communication;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class AppController {
    @Autowired
    JsonReader jsonReader;
    @Autowired
    TranslateData translateData;
    @Autowired
    Communication communication;

    @GetMapping("/data")
    public String data(Map<String, Object> model) throws SQLException {
        model.put("messages", new DataService().getAll());
        return "data";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("lang", jsonReader.getLanguage());
        return "main";
    }

    @PostMapping
    public String translateText(
            @ModelAttribute("TextForTranslate") TextForTranslate textForTranslate,
            Map<String, Object> model
    ) throws JsonProcessingException, SQLException {
        Data data = new Data();
        String requestId = GenId.getStringId();

        String sourceLang = textForTranslate.sourceLang;
        String targetLang = textForTranslate.targetLang;
        data.setRequestId(requestId);
        data.setRequestLang(sourceLang);
        data.setResponseLang(targetLang);
        translateData.setSourceLanguageCode(sourceLang);
        translateData.setTargetLanguageCode(targetLang);
        StringJoiner joiner = new StringJoiner(" ");
        String[] words = textForTranslate.text.trim().split(" ");
        if (!words[0].isEmpty()) {
            for (String word : words) {
                Long id = GenId.getLongId();
                translateData.setTexts(Collections.singletonList(word));
                String translateWord = communication.translate(translateData);

                data.setId(id);
                data.setDateTime(new Timestamp(System.currentTimeMillis()));
                data.setRequest(word);
                data.setResponse(translateWord);
                data.setResponseIp("1.2.3.4");

                new DataService().add(data);
                joiner.add(translateWord);
            }
        }
        model.put("result", joiner.toString());
        model.put("messages", textForTranslate);
        model.put("lang", jsonReader.getLanguage());
        return "main";
    }
}
