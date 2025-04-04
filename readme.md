# 클린 아키텍처 참고용
### 클린 아키텍처의 기본 원칙
- 의존성 규칙 (Dependency Rule): 바깥 계층(컨트롤러, 어댑터)이 안쪽 계층(도메인, UseCase)을 의존해야 하며, 반대는 안 됨.
- 추상화에 의존 (Depend on Abstractions): 구체적인 구현(Concrete Class)이 아닌 추상화(Interface)에 의존해야 함.
- 책임 분리: 각 계층이 명확한 책임을 가져야 함.
### 주의
- 도메인 계층과 영속성 계층이 분리되어 있고 엔티티도 분리되어 있으므로 JPA 의 dirty checking 이 작동하지 않는다.

각 계층에 있는 readme 도 참고

-  각 계층 간 DTO 변환을 명확하게 수행하여 의존성 규칙을 준수
-  User (도메인 모델)과 UserJpaEntity (영속성 모델)를 분리
-  adapter.in (Controller)에서 DTO 변환을 수행하고, UseCase 인터페이스만 호출
-  adapter.out (Persistence)에서 DB 엔티티를 변환 후 반환
-  application (Service)에서는 순수한 도메인 로직만 처리

### 추가
- actuator
- prometheus
- grafana
- loki

### 작업
- 페이징 처리
- queryDSL