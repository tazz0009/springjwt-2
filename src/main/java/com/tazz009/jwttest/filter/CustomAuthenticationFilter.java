package com.tazz009.jwttest.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*
 * 로그인 양식은 이 필터에 대해 사용자 이름과 비밀번호라는 두 가지 매개변수를 제공해야 합니다. 
 * 사용할 기본 매개변수 이름은 
 * 정적 필드 SPRING_SECURITY_FORM_USERNAME_KEY 및 SPRING_SECURITY_FORM_PASSWORD_KEY에 포함됩니다. 
 * 매개변수 이름은 usernameParameter 및 passwordParameter 속성을 설정하여 변경할 수도 있습니다.
 */
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	/*
	 * 실제 인증을 수행합니다.
	 * 
	 * 구현은 다음 중 하나를 수행해야 합니다.
	 * 1. 성공적인 인증을 나타내는 인증된 사용자에 대해 채워진 인증 토큰을 반환합니다.
	 * 2. 인증 프로세스가 아직 진행 중임을 나타내는 null을 반환합니다. 
	 *    	반환하기 전에 구현은 프로세스를 완료하는 데 필요한 추가 작업을 수행해야 합니다.
	 * 3. 인증 프로세스가 실패하면 AuthenticationException을 throw합니다.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, String[]> parameterMap = request.getParameterMap();
		MapUtils.debugPrint(System.out, "attemptAuthentication", parameterMap);
		log.info("Username is: {}", username);
		log.info("Password is: {}", password);
		
		/*
		 * 사용자 이름과 비밀번호의 간단한 표시를 위해 설계된 
		 * org.springframework.security.core.Authentication 구현.
		 * 
		 * isAuthenticated()가 false를 반환하므로 
		 * 이 생성자는 UsernamePasswordAuthenticationToken을 생성하려는 
		 * 모든 코드에서 안전하게 사용할 수 있습니다.
		 */
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();

		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10분 추가
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		String refresh_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30분 추가
				.withIssuer(request.getRequestURI().toString())
				.sign(algorithm);
		
//		response.setHeader("access_token", access_token);
//		response.setHeader("refresh_token", refresh_token);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
	
}
