package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	@Qualifier("yandexRestTemplate")
	public RestTemplate restTemplate(@Value("${translate.url}") String url) {
		return new RestTemplateBuilder()
				.rootUri(url)
				.build();
	}
}
