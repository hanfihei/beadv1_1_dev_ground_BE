## ✏️ 프로젝트 개요
**DBAY**는 전자기기 중고거래 이용자를 위해, 신뢰할 수 있는 거래 환경과 **예치금 기반 안전 결제·정산, 실시간 검색·비딩을 제공하는 중고거래 플랫폼**입니다.</br>

## 📪 배포 링크
### [DBAY](https://dbay.site/)

## 📆 개발 기간
- **전체 개발 기간** : 2025.11.03 - 2025.12.19
- **주제 선정 및 기획** : 2025.11.03. ~ 2025.11.06
- **기능명세서 작성 및 역할 분배** : 2025.11.07 ~ 2025.11.10
- **기능 구현** : 2025.11.10 ~ 2025.12.18
- **마무리 및 발표준비** : 2025.12.18 ~ 2025.12.19

## 👨‍💻 개발인원 및 역할
| BE                                                                            | BE                                                                             | BE                                                                                 | BE                                                                                  | BE                                                                             |
|-------------------------------------------------------------------------------|--------------------------------------------------------------------------------|------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| <img src="https://avatars.githubusercontent.com/ljs981026" width=175 alt="이재석"> | <img src="https://avatars.githubusercontent.com/oneplast" width=175 alt="민경훈"> | <img src="https://avatars.githubusercontent.com/Baeyonghyeon" width=175 alt="배용현"> | <img src="https://avatars.githubusercontent.com/u/75302306?v=4" width=175 alt="진세현"> | <img src="https://avatars.githubusercontent.com/hanfihei" width=175 alt="한지혜"> |
| [이재석](https://github.com/ljs981026) **(팀장)**                                  | [민경훈](https://github.com/oneplast)                                             | [배용현](https://github.com/Baeyonghyeon)                                             | [진세현](https://github.com/niki8533)                                                                             | [한지혜](https://github.com/hanfihei)                                             |
| 장바구니, 주문 도메인 담당                                                               | 상품, 이미지 도메인 담당                                                                 | 예치금, 정산, 배포 담당                                                                     | 유저, 결제 도메인 담당                                                              | 채팅, 인프라 담당                                                                     |

## 🔊 주요 기능
#### 1. 회원 및 마이페이지
- 회원가입 / 로그인 / 로그아웃
- 소셜로그인 지원(kakao)
- 마이페이지에서 거래 상품 및 거래 상태 확인
- 사용자 판매 상품 조회
#### 2. 상품 관리 및 이미지
- 상품 등록, 수정, 삭제, 조회
- 상품 이미지 등록/추가/삭제 및 상품 이미지 기반 관리 흐름
#### 3. 검색 및 탐색
- **키워드 검색** : 자동완성, 연관 검색어 태그 제공
- **카테고리 기반 검색** : 상위 카테고리 선택 시 하위 카테고리 포함
- **가격 필터링, 정렬**: 최신순 / 가격순 / 업데이트 순 등
#### 4. 장바구니 및 주문
- 장바구니 추가/삭제/조회
- 장바구니 상품 주문(묶음 주문)
#### 5. 거래 및 결제
- 등록된 상품을 예치금으로 구매
- 상품 결제 시 예치금 차감
- 토스 페이(Toss) API로 예치금 충전/결제
#### 6. 정산
- 배달 완료 후 2주 경과한 거래 대상으로 정산 처리
#### 7. 채팅
- 판매자와 1:1 채팅
- 채팅방 목록 확인, 채팅방 선택
- 실시간 읽음 표시, 이전 메시지 저장
#### 8. 상품 추천
- 사용자 조회 이력 기반 추천(최근 7일 기준)
- 상품 상세에서 유사 상품 추천
- 이력이 부족하면 인기 상품 추천
#### 9. 아키텍처/운영
- Gateway/Eureka 기반 서비스 구성
- Elasticsearch, Kibana, Redis, Kafka 등 연동
- Kubernetes/Docker 기반 배포 흐름

## ⚒️ 개발 환경
### 📜 Project Docs
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"> <img src="https://img.shields.io/badge/ErdCloud-9370DB?style=for-the-badge&logo=ErdCloud&logoColor=white">

### 🖥 **System Architecture**
<img src="https://github.com/user-attachments/assets/b90c9a5a-1d07-4ea0-bab4-86c8fe4aa20b" alt="System Architecture">

### 🖥 **ERD Diagram**
<img src="https://github.com/user-attachments/assets/dd5bf20b-4540-4d22-a855-1b141387c27c" alt="ERD Diagram">

### 🛠 Tech Stack
💻 **Development**  
<img src="https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white">  
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

⚡ **Real-Time**  
<img src="https://img.shields.io/badge/WebSocket-0088CC?style=for-the-badge&logo=websocket&logoColor=white"> <img src="https://img.shields.io/badge/STOMP-009ACE?style=for-the-badge&logo=apache&logoColor=white"> <img src="https://img.shields.io/badge/SSE-FF9900?style=for-the-badge&logo=eventbrite&logoColor=white">

🗄 **Database & ORM**  
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  
<img src="https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=hibernate&logoColor=white"> 

📑 **Docs & API Testing**  
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">

📊 **Observability & Monitoring**  
<img src="https://img.shields.io/badge/ElasticSearch-005571?style=for-the-badge&logo=ElasticSearch&logoColor=white"> <img src="https://img.shields.io/badge/Kibana-005571?style=for-the-badge&logo=Kibana&logoColor=white">

📨 **Messaging / Event Streaming**  
<img src="https://img.shields.io/badge/Apache Kafka-231F20?style=for-the-badge&logo=Apache Kafka&logoColor=white">

🔐 **Authentication & Security**  
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white"> <img src="https://img.shields.io/badge/OAuth 2.0-3D5AFE?style=for-the-badge&logo=oauth&logoColor=white">

🧪 **Testing**  
<img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"> <img src="https://img.shields.io/badge/Mockito-FFCD00?style=for-the-badge&logo=java&logoColor=black">

🤝 **Collaboration Tools**  
<img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white"> <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">

🚀 **Deployment**  
<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white">

🔗 **External APIs**  
<img src="https://img.shields.io/badge/Kakao API-FFCD00?style=for-the-badge&logo=kakao&logoColor=black"> <img src="https://img.shields.io/badge/Toss API-0074E4?style=for-the-badge&logo=toss&logoColor=white">

---






## 👩‍💻 담당 역할

### 1) 실시간 채팅 기능 구현 (Core)
- WebSocket **STOMP 기반 실시간 채팅 송수신**
- 채팅방 생성 / 목록 조회 / 메시지 내역 조회
- 메시지 **읽음 처리 + 실시간 읽음 이벤트 전송**
- Kafka 기반 이벤트 발행/구독으로 비동기 처리
- MongoDB 기반 메시지 저장 구조 설계 및 적용

### 2) 배포 & CI/CD (Infra / DevOps)
- AWS EC2 기반 서비스 배포
- Docker / Docker Compose 운영 환경 구성
- GitHub Actions 기반 CI/CD 자동 배포 구축
- Nginx 리버스 프록시 설정 및 운영 대응

---

## ✨ 주요 기능

### 실시간 채팅 (WebSocket STOMP)
- 사용자는 실시간으로 메시지를 주고받을 수 있음
- 채팅방 단위로 메시지 구독(`/topic/chat/{chatId}`) 구조 적용
- 채팅 화면 진입 시 읽음 처리 이벤트 반영

### 읽음 처리 (Read Receipt)
- 상대방 메시지 중 읽지 않은 메시지만 조회
- 읽음 처리 후 DB 반영 + 실시간 브로드캐스트

### Kafka 이벤트 기반 처리
- 메시지 전송 / 읽음 처리 이벤트를 Kafka로 발행
- Listener가 이벤트를 구독하고 WebSocket으로 브로드캐스트
- msa구조에 적합함

---

## 시스템 아키텍처 (Chat)

### 흐름 요약
1. Client → WebSocket(`/ws-chat`) 연결  
2. 메시지 발행 → `/app/chat.send`  
3. 서버에서 메시지 저장 + Kafka 이벤트 발행  
4. Kafka Consumer가 이벤트 수신  
5. WebSocket 구독 채널로 브로드캐스트  
   - `/topic/chat/{chatId}`
   - `/topic/chat/{chatId}/read`

---

## 📌 채팅 기능 상세 설계

### 1) REST API
- 채팅방 생성
- 채팅방 목록 조회
- 채팅 메시지 조회
- 채팅방 나가기
- 채팅 읽음 처리 요청

  
### 2) WebSocket STOMP 설계
- Endpoint: `/ws-chat`
- App Prefix: `/app`
- Broker: `/topic`


### 3) MongoDB 구조

#### ChatRoom
- id
- chatCode
- productCode
- sellerCode
- buyerCode
- status (OPEN / CLOSED)
- lastMessage
- lastMessageAt
- sellerLastReadAt
- buyerLastReadAt
- sellerUnreadCount
- buyerUnreadCount

#### ChatMessage
- id
- chatId
- senderCode
- message
- createdAt

---

## ⚠️ Troubleshooting – 채팅방 목록 조회 성능 개선 (N+1)

### 문제
채팅방 목록 조회 시, 각 채팅방마다 Product 서비스로 Feign 호출이 발생하여  
채팅방 수에 비례해 외부 호출이 증가하는 **N+1 문제**가 발생함.

---

### 해결
- 채팅방 목록에서 `productCode`만 수집
- Product 서비스 배치 조회 API 1회 호출
- 응답을 `Map<productCode, title>`로 변환
- 채팅방 루프에서는 Map 조회만 수행

```java
List<String> productCodes = rooms.stream()
    .map(ChatRoom::getProductCode)
    .distinct()
    .toList();

List<CartProductsResponse> products =
    productClient.getCartProducts(new CartProductsRequest(productCodes)).data();

Map<String, String> productTitleMap = products.stream()
    .collect(Collectors.toMap(
        CartProductsResponse::productCode,
        CartProductsResponse::title
    ));



## 📊 성능 테스트 결과 (nGrinder)

- 테스트 도구: nGrinder
- 가상 사용자(VUser): 500
- 테스트 시간: 30초
- 대상 API: `GET /api/chat/rooms`
- 동일 스크립트로 개선 전/후 비교

### 🔴 개선 전 (N+1 존재)
- Mean Test Time: 4,056 ms
- TPS: 102
- Peak TPS: 131
- Executed Tests: 1,558
- Errors: 0

### 🟢 개선 후 (N+1 제거)
- Mean Test Time: 2,762 ms
- TPS: 145.4
- Peak TPS: 215
- Executed Tests: 2,190
- Errors: 137

### 결과 해석
- Feign 호출을 채팅방 수만큼 수행하던 구조를 배치 조회 방식으로 변경하여  
  호출 횟수를 **N → 1**로 감소시킴
- 평균 응답 시간은 **약 32% 감소**
- 동일 부하 조건에서 TPS는 **약 42% 증가**
- 처리량 증가로 일부 요청에서 타임아웃 에러가 발생했으나,  
  이는 병목 제거 후 서버가 더 많은 요청을 수용한 결과로 해석함

### 결론
- 구조적 성능 병목(N+1)을 제거하여 응답 속도와 처리량을 동시에 개선

