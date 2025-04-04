package lime1st.example.clean.user.application.dto.query;

import java.util.List;

// List<Entity>Query 엔티티 목록 조회
public record ListUserQuery(
        List<FindUserQuery> queries
) {
}
