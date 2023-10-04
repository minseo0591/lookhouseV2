---
description: 회원가입 및 로그인 , 로그아웃
---

# 😀 User

{% swagger method="post" path="//localhost:8080/api/member/email_check" baseUrl="http:" summary="이메일 인증합니다." %}
{% swagger-description %}
email로 인증번호를 전송합니다.
{% endswagger-description %}

{% swagger-parameter in="body" name="email" type="String" required="true" %}
email 인증
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="인증번호를 전송했습니다" %}

{% endswagger-response %}
{% endswagger %}

{% swagger method="post" path="i/member" baseUrl="http://localhost:8080/ap" summary="회원가입하기" %}
{% swagger-description %}
이메일, 인증번호, 닉네임, 비밀번호를 확인하고 DB에 저장합니다.
{% endswagger-description %}

{% swagger-parameter in="body" name="email" type="String" required="true" %}
이메일
{% endswagger-parameter %}

{% swagger-parameter in="body" name="checkCode" type="String" required="true" %}
이메일 인증번호
{% endswagger-parameter %}

{% swagger-parameter in="body" name="password" required="true" type="String" %}
비밀번호
{% endswagger-parameter %}

{% swagger-parameter in="body" name="nickName" type="String" required="true" %}
닉네이ㅁ
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="회원가입 완료" %}

{% endswagger-response %}
{% endswagger %}

{% swagger method="post" path=" " baseUrl="http://localhost:8080/login" summary="로그인 하기" %}
{% swagger-description %}
이메일과 패스워드로 로그인 합니다.
{% endswagger-description %}

{% swagger-parameter in="body" name="email" required="true" type="String" %}

{% endswagger-parameter %}

{% swagger-parameter in="body" name="password" type="String" required="true" %}

{% endswagger-parameter %}

{% swagger-response status="200: OK" description="로그인 성공" %}

{% endswagger-response %}
{% endswagger %}

{% swagger method="post" path=" " baseUrl="http://localhost:8080/logout" summary="로그아웃 하기" %}
{% swagger-description %}
로그아웃 할 수 있습니다.
{% endswagger-description %}
{% endswagger %}
