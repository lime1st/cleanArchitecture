package lime1st.example.clean.user.application.dto.query;

import lime1st.example.clean.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

// Find<Entity>query 특정 엔티티 조회
@Builder
public record FindUserQuery(
        UUID id,
        String name,
        String email,
        String password,
        String deletedBy,
        LocalDateTime deletedAt
) {

    public User to() {
        return new User(
                id,
                name,
                email,
                password,
                deletedBy,
                deletedAt
        );
    }
}
