# 가격 비교 서비스

가격 비교 서비스를 제공하는 BACK-END API 서버입니다.

## 서비스 소개
Kotlin 코루틴 기반으로 여러 제휴사 API를 비동기·논블로킹 방식으로 병렬 호출/수신하는 상품 연동 시뮬레이션 시스템입니다.

[프로젝트 총 정리글](https://velog.io/@maxxyoung/series/Async-API)
## 프로젝트 목표
- 비동기·논블로킹 기술의 이해 높인다.
- 학습, 실무를 통해서 적용해보고 싶었던 기술 익힌다.

## 아키텍처 설계 및 스키마 도출
[아키텍처 설계 및 스키마 도출](https://velog.io/@maxxyoung/async-DB-%EC%84%A4%EA%B3%84)
## ERD
![image](https://github.com/user-attachments/assets/bdb232bd-26d3-486c-861f-dd1bfdac8709)

## 사용 기술 스택
| 영역    | 기술 |
|--------|------|
| Framework | Spring Boot 3.4.2, Kotlin, JPA |
| Database  | MySQL |
| Cache     | Redis |
| Messaging | Kafka |
| Infra     | Docker |

## 서버 구조도
![image](https://github.com/user-attachments/assets/f8846ccc-e1ce-424c-bb38-4b38b1e6b80d)

요청이 들어오면 Redis 캐시에서 가격 비교 결과를 먼저 조회합니다.  
캐시 미스가 발생할 경우, Kafka 메시지로 발행되어 Consumer가 비동기적으로 제휴사 API 호출을 수행합니다. 코루틴을 사용해 여러 제휴사 API를 비동기·논블로킹 방식으로 병렬 요청합니다.  
요청 결과는 제휴사가 현재 서버의 결과 전달 API를 호출해 받아옵니다.   

## 이슈 정리
- [대규모 외부 API 호출 시 스레드 점유 최소화는? → Kotlin 코루틴 통한 비동기·논블로킹 처리](https://velog.io/@maxxyoung/async-%EC%97%AC%EB%9F%AC-%EA%B0%9C%EC%9D%98-API%EB%A5%BC-%ED%98%B8%EC%B6%9C-%EC%BD%94%EB%A3%A8%ED%8B%B4-%EC%A0%81%EC%9A%A9)
- [요청 처리의 확장성과 캐시 미스 대응은? → Kafka 기반 비동기 메시지 처리 구조 적용](https://velog.io/@maxxyoung/async-Kafka%EB%A5%BC-%ED%86%B5%ED%95%9C-%EB%B6%84%EC%82%B0-%EC%B2%98%EB%A6%AC)
