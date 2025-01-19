package com.kyulab.gateway.filter;

import com.kyulab.gateway.dto.TokenDTO;
import com.kyulab.gateway.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFilter implements GatewayFilter {

	private final TokenService tokenService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		HttpMethod method = request.getMethod();

		if (method.matches("GET")) {
			return chain.filter(exchange);
		}

		final String token = tokenService.getTokenFromCookie(request);
		if (token.isEmpty()) {
			log.error("TokenDTO is not include");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		if (!tokenService.validToken(token)) { // access 토큰 인증 실패일 경우
			String userId = tokenService.getUserIdFromToken(token);
			String refreshToken = tokenService.getRefreshToken(userId);
			if (refreshToken != null) { // 리프레쉬 토큰이 유효하다면
				TokenDTO tokenDTO = tokenService.parseDataByToken(refreshToken);
				String accessToken = tokenService.createAccessToken(tokenDTO);
				String cookie = tokenService.makeCookieString("ACCESS", accessToken, false);
				exchange.getResponse().getHeaders().set(HttpHeaders.SET_COOKIE, cookie);
			} else {
				String clearToken = tokenService.makeCookieString("ACCESS", "", true);
				exchange.getResponse().getHeaders().set(HttpHeaders.SET_COOKIE, clearToken);
				return exchange.getResponse().setComplete();
			}
		}
		return chain.filter(exchange);
	}

}
