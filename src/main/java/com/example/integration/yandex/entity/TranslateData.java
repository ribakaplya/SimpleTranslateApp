package com.example.integration.yandex.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TranslateData {

    private String sourceLanguageCode;
    private String targetLanguageCode;
    private List<String> texts;
    private String folderId;
    private Boolean speller;

    public String getSourceLanguageCode() {
        return sourceLanguageCode;
    }

    public void setSourceLanguageCode(String sourceLanguageCode) {
        this.sourceLanguageCode = sourceLanguageCode;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public void setSpeller(Boolean speller) {
        this.speller = speller;
    }

    public Boolean getSpeller() {
        return speller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslateData that = (TranslateData) o;
        return Objects.equals(sourceLanguageCode, that.sourceLanguageCode) && Objects.equals(targetLanguageCode, that.targetLanguageCode) && Objects.equals(texts, that.texts) && Objects.equals(folderId, that.folderId) && Objects.equals(speller, that.speller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceLanguageCode, targetLanguageCode, texts, folderId, speller);
    }

    @Override
    public String toString() {
        return "TranslateData{" +
                "sourceLanguageCode='" + sourceLanguageCode + '\'' +
                ", targetLanguageCode='" + targetLanguageCode + '\'' +
                ", texts=" + texts +
                ", folderId='" + folderId + '\'' +
                ", speller=" + speller +
                '}';
    }
}
