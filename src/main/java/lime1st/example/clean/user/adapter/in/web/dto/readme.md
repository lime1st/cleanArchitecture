## 표현 계층(Presentation Layer)

### 🔹 Request DTO (클라이언트 → 서버 요청)

클라이언트가 API 에 데이터를 보낼 때 사용하는 DTO 입니다.

주로 `@RequestBody`, `@RequestParam`, `@PathVariable` 등의 입력으로 사용됩니다.


### 🔹 Response DTO (서버 → 클라이언트 응답)

API 응답을 최적화하기 위한 DTO 입니다.

도메인 객체의 모든 필드를 노출하지 않고, **필요한 필드만 포함**하여 **응답을 최적화**합니다.

**Request & Response DTO 는 표현 계층에서 클라이언트와 데이터를 송수신하는 역할**
    - `CreateUserRequest`, `FindUserResponse` 등

**각 DTO 의 역할을 명확하게 구분하면 유지보수성과 확장성이 좋아짐**
    - 표현 계층의 변경이 응용 계층에 영향을 주지 않음
    - 응용 계층의 DTO 는 도메인 모델과 독립적으로 관리 가능