package com.example.integration.yandex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TranslateResult {
	@JsonProperty("translations")
	private List<TranslateResultText> translations;

	public List<TranslateResultText> getTranslations() {
		return translations;
	}

	public void setTranslations(List<TranslateResultText> translations) {
		this.translations = translations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TranslateResult that = (TranslateResult) o;
		return Objects.equals(translations, that.translations);
	}

	@Override
	public int hashCode() {
		return Objects.hash(translations);
	}

	@Override
	public String toString() {
		return "TranslateResult{" +
		       "translations=" + translations +
		       '}';
	}

	public static class TranslateResultText {
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
}
