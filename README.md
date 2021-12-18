# Spring Security Test

## chap00. User, Role service/domain/repository 등 생성

- test

1. localhost:8080/api/users 접속
1. user / Using generated security password로 로그인 
1. DB에 있는 user 정보가 나온다.

## chap01. SpringSecurity 세팅

1. CorsFilter 생성
1. SecurityConfig 생성
1. UserService에 UserDetailsService 추가
1. CustomAuthenticationFilter 추가

- test 
1. localhost:8080/api/users 접속
1. post username:tazz001 / password: 1234 로 로그인 
1. access_token, refresh_token 확인가능
1. https://jwt.io/ 토큰 내용 확인가능


