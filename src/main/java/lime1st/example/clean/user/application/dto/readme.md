## 🔹 Command DTO (쓰기 용도)

- 비즈니스 로직을 실행하는 요청을 위한 DTO 입니다.

- **유즈 케이스(Use Case) 단위**로 사용되며, 도메인 모델을 직접 노출하지 않고 필요한 데이터만 전달합니다.


## 🔹 Query DTO (조회 용도)

- 데이터 조회를 최적화하기 위한 DTO 입니다.

- 도메인 객체를 직접 노출하지 않고 필요한 필드만 포함하여 응답을 최적화합니다.


📌 **예제**

- `FindUserQuery` (`FindUserQuery(1L)` → ID가 `1L`인 `User` 엔티티를 조회)
- `ListOrdersQuery`
- `GetUserEmailQuery` (`GetUserEmailQuery(1L)` → ID가 `1L`인 사용자의 `email`만 조회)
- `SearchCustomersQuery`

**Command & Query DTO는 응용 계층에서 비즈니스 로직을 실행/조회하는 역할**
    - `CreateOrderCommand`, `FindUserQuery` 등