# 클린 아키텍처 참고용
각 계층에 있는 readme 도 참고

com.example.user
├── application  (Use Case 계층)
│   ├── port
│   │   ├── in
│   │   │   ├── CreateUserUseCase.java
│   │   │   ├── GetUserUseCase.java
│   │   ├── out
│   │       ├── LoadUserPort.java
│   │       ├── SaveUserPort.java
│   ├── service
│       ├── UserService.java
│
├── domain  (엔티티 계층)
│   ├── User.java
│
├── adapter  (어댑터 계층)
│   ├── in
│   │   ├── web
│   │       ├── UserController.java
│   ├── out
│   │   ├── persistence
│   │       ├── UserJpaEntity.java
│   │       ├── UserRepository.java
│   │       ├── UserPersistenceAdapter.java
│
├── config  (설정 관련)
│   ├── BeanConfig.java
│
├── UserApplication.java  (메인 애플리케이션)


DTO 나 JPA Entity 를 직접 노출하지 않고 각 계층 간 데이터 변환을 수행하는 계층
📂 user-service
┣ 📂 application      // (UseCase 구현 - 서비스 로직)
┃ ┣ 📂 port          // (인터페이스 - adapter 에게 구현을 강제)
┃ ┣ 📜 CreateUserUseCase.java
┃ ┣ 📜 GetUserUseCase.java
┃ ┣ 📜 UserService.java
┃
┣ 📂 domain           // (순수한 도메인 객체)
┃ ┣ 📜 User.java
┃ ┣ 📜 UserValidator.java
┃
┣ 📂 adapter
┃ ┣ 📂 in            // (Controller, API Adapter - DTO 변환 처리)
┃ ┃ ┣ 📜 UserController.java
┃ ┃ ┣ 📜 CreateUserRequest.java  // 요청 DTO
┃ ┃ ┣ 📜 UserResponse.java       // 응답 DTO
┃ ┃
┃ ┣ 📂 out           // (DB, 외부 API Adapter)
┃ ┃ ┣ 📜 UserPersistenceAdapter.java
┃ ┃ ┣ 📜 UserJpaEntity.java   // JPA Entity
┃ ┃ ┣ 📜 UserRepository.java  // JpaRepository
┃ ┃ ┣ 📜 SaveUserPort.java
┃ ┃ ┣ 📜 LoadUserPort.java
┃
┗ 📂 config          // (Spring 설정)

✅ 각 계층 간 DTO 변환을 명확하게 수행하여 의존성 규칙을 준수
✅ User (도메인 모델)과 UserJpaEntity (영속성 모델)를 분리
✅ adapter.in (Controller)에서 DTO 변환을 수행하고, UseCase 인터페이스만 호출
✅ adapter.out (Persistence)에서 DB 엔티티를 변환 후 반환
✅ application (Service)에서는 순수한 도메인 로직만 처리