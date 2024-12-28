package com.kyulab.gateway.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteOption {

	private String id;
	private String uri;
	private List<String> predicates;

}
