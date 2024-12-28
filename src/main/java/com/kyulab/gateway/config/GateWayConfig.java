package com.kyulab.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GateWayConfig {

	// 추후 yml을 이용한 명시
	private final GateWayProperties gateWayProperties;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service", r -> r.path("/api/user/**").uri("http://localhost:8001"))
				.route("board-service", r -> r.path("/api/board/**").uri("http://localhost:8002"))
				.build();
	}

}


