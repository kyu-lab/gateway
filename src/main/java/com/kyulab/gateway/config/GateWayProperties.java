package com.kyulab.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class GateWayProperties {

	private List<RouteConfig> routes;

	public List<RouteConfig> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteConfig> routes) {
		this.routes = routes;
	}

}
