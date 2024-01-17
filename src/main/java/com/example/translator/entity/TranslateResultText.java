package com.example.translator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TranslateResultText {
    @JsonProperty("text")
    private String text;

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
        TranslateResultText that = (TranslateResultText) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "TranslateResultText{" +
                "text='" + text + '\'' +
                '}';
    }
}
