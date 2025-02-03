package com.kyulab.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebConfig {

	@Bean
	public CorsWebFilter corsWebFilter() {
		// CORS 설정을 위한 CorsConfiguration 객체 생성
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "https://localhost:3000"));
		corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.addAllowedHeader("*"); // 모든 헤더 허용
		corsConfiguration.setAllowCredentials(true); // 자격 증명 허용

		// CORS 설정을 적용할 URL 패턴 지정
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsWebFilter(source); // WebFlux 방식으로 CORS 필터 반환
	}

}
