package com.kyulab.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class GateWayProperties {

	private List<RouteOption> routes;

	public List<RouteOption> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteOption> routes) {
		this.routes = routes;
	}

}
