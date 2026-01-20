# 💬 채팅 서비스 포트폴리오 - 실시간 소통 & 배포 담당

> **WebSocket STOMP 기반 실시간 채팅 + Kafka 이벤트 처리 + AWS 배포/운영까지 담당한 백엔드 프로젝트**
>
> 본 프로젝트에서 저는 **채팅 기능 전반 구현**과 **CI/CD 기반 배포 자동화 및 운영 환경 구성**을 담당했습니다.  
> 실시간 송수신, 읽음 처리, 메시지 저장 구조를 설계하고, 배포 파이프라인을 통해 안정적인 운영까지 경험했습니다.

**📅 개발 기간**: (예: 2025.xx.xx ~ 2025.xx.xx)  
**👥 인원**: 4명 (Backend)

---

## 👩‍💻 담당 역할 (My Role)

### ✅ 1) 실시간 채팅 기능 구현 (Core)
- WebSocket **STOMP 기반 실시간 채팅 송수신**
- 채팅방 생성 / 목록 조회 / 메시지 내역 조회
- 메시지 **읽음 처리 + 실시간 읽음 이벤트 전송**
- Kafka 기반 이벤트 발행/구독으로 비동기 처리
- MongoDB 기반 메시지 저장 구조 설계 및 적용

### ✅ 2) 배포 & CI/CD (Infra / DevOps)
- AWS EC2 기반 서비스 배포
- Docker / Docker Compose 운영 환경 구성
- GitHub Actions 기반 CI/CD 자동 배포 구축
- Nginx 리버스 프록시 설정 및 운영 대응

---

## ✨ 주요 기능

### 💬 실시간 채팅 (WebSocket STOMP)
- 사용자는 실시간으로 메시지를 주고받을 수 있음
- 채팅방 단위로 메시지 구독(`/topic/chat/{chatId}`) 구조 적용
- 채팅 화면 진입 시 읽음 처리 이벤트 반영

### ✅ 읽음 처리 (Read Receipt)
- 상대방 메시지 중 **읽지 않은 메시지만 조회**
- 읽음 처리 후 DB 반영 + 실시간 브로드캐스트

### 📦 Kafka 이벤트 기반 처리
- 메시지 전송 / 읽음 처리 이벤트를 Kafka로 발행
- Listener가 이벤트를 구독하고 WebSocket으로 브로드캐스트
- 서비스 확장(MSA) 시에도 구조 유지 가능

---

## ⚒ 기술 스택

### Backend
- Java, Spring Boot
- Spring WebSocket (STOMP)
- Spring Data MongoDB / JPA
- Kafka

### DB
- MongoDB (채팅 메시지/방)
- (MySQL/Redis 등 사용했다면 추가)

### Infra
- AWS EC2 / S3
- Docker / Docker Compose
- Nginx
- GitHub Actions

---

## ☁️ 시스템 아키텍처 (Chat 중심)

### ✅ 흐름 요약
1. Client → WebSocket(`/ws-chat`) 연결  
2. 메시지 발행 → `/app/chat.send`  
3. 서버에서 메시지 저장 + Kafka 이벤트 발행  
4. Kafka Consumer가 이벤트 수신  
5. WebSocket 구독 채널로 브로드캐스트  
   - `/topic/chat/{chatId}`
   - `/topic/chat/{chatId}/read`

---

## 📌 채팅 기능 상세 설계

### 1) REST API (채팅방/내역)
- 채팅방 생성
- 채팅방 목록 조회
- 채팅 메시지 조회(시간순)
- 채팅방 나가기
- 채팅 읽음 처리

예시 URI:
- `POST /api/chat/rooms`
- `GET /api/chat/rooms`
- `GET /api/chat/messages/{chatId}`
- `POST /api/chat/read/{chatId}`
- `DELETE /api/chat/rooms/{chatId}`

---

### 2) WebSocket STOMP 설계
- Endpoint: `/ws-chat`
- App Prefix: `/app`
- Broker: `/topic`

예시:
- 발행(Publish): `/app/chat.send`
- 구독(Subscribe): `/topic/chat/{chatId}`
- 읽음 구독: `/topic/chat/{chatId}/read`

---

### 3) MongoDB 저장 구조 (예시)

#### ✅ ChatRoom
- chatId
- productCode
- sellerCode
- buyerCode
- status (OPEN/CLOSED)
- createdAt

#### ✅ ChatMessage
- chatId
- senderCode
- content
- createdAt
- isRead
- readAt

---

## ⚠️ 트러블슈팅 (Troubleshooting)

### ✅ 1) 읽음 처리 설계 개선

#### 🔍 문제 상황
- 사용자가 채팅방에 들어오면 “내가 보지 않은 메시지”만 읽음 처리해야 함  
- 전체 메시지 업데이트 시 불필요한 DB 부하 발생 가능

#### 🛠️ 해결 방법
- 조회 조건을 명확히 분리해 **상대가 보낸 + 안읽은 메시지만 조회**
```java
List<ChatMessages> unread =
    messageRepository.findByChatIdAndSenderCodeNotAndIsReadFalse(chatId, readerCode);




# beadv1_1_dev_ground_BE
2팀: DevGround 팀의 MSA 이커머스 세미 프로젝트 백엔드 Reporisotry
