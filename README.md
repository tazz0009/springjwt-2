# Spring Security Test

## chap00. User, Role service/domain/repository 등 생성

- test

1. localhost:8080/api/users 접속
1. user / Using generated security password로 로그인 
1. DB에 있는 user 정보가 나온다.

## chap01. SpringSecurity 세팅 - token 생성

1. CorsFilter 생성
1. SecurityConfig 생성
1. UserService에 UserDetailsService 추가
1. CustomAuthenticationFilter 추가

- postman test 
1. localhost:8080/api/users 접속
1. post username:tazz001 / password: 1234 로 로그인 
1. access_token, refresh_token 확인가능
1. https://jwt.io/ 토큰 내용 확인가능

## chap02. SpringSecurity 세팅 - token 확인

1. CustomAuthorizationFilter 추가 - head 내의 token을 확인하는 필터

- postman test 
1. localhost:8080/api/users 접속 - 403 에러
1. localhost:8080/api/users 헤더에 Authorization 에 bear 임의의 문자 입력 - 403 에러
1. localhost:8080/api/users 헤더에 Authorization 에 bear JWT 문자 입력 - 정상출력

## chap03. SpringSecurity 세팅 - refresh token 
