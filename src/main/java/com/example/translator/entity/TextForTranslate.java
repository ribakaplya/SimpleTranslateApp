package com.example.translator.entity;

import java.util.Objects;

public class TextForTranslate {
    public String sourceLang;
    public String targetLang;
    public String text;

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextForTranslate that = (TextForTranslate) o;
        return Objects.equals(sourceLang, that.sourceLang) && Objects.equals(targetLang, that.targetLang) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceLang, targetLang, text);
    }

    @Override
    public String toString() {
        return "TextForTranslate{" +
                "sourceLang='" + sourceLang + '\'' +
                ", targetLang='" + targetLang + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
