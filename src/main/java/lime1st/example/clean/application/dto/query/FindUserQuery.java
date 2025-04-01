package lime1st.example.clean.application.dto.query;

// Find<Entity>query 특정 엔티티 조회
public record FindUserQuery(
        String name,
        String email,
        String password
) {
}
