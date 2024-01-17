package com.example.translator.rest;

import com.example.translator.entity.TranslateData;
import com.example.translator.rest.configuration.RestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestApp {
    public void requestTranslate(TranslateData translateData) throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        String transleteList = communication.translate(translateData);
        System.out.println(transleteList);
    }
}
