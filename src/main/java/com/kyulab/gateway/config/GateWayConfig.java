package com.kyulab.gateway.config;

import com.kyulab.gateway.filter.BoardFilter;
import com.kyulab.gateway.filter.UserFilter;
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
	private final UserFilter userFilter;
	private final BoardFilter boardFilter;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service", r -> r.path("/api/user/**")
						.filters(f -> f.filter(userFilter))
						.uri("http://localhost:8001")
				)
				.route("board-service",
						r -> r.path(
							"/api/board/**",
							"/api/post/**",
							"/api/comment/**"
						)
						.filters(f -> f.filter(boardFilter))
						.uri("http://localhost:8002")
				)
				.build();
	}

}


