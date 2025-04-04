package lime1st.example.clean.user.adapter.in.web.dto.response;

import lime1st.example.clean.user.application.dto.query.ListUserQuery;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

// List<Entity>Response 목록 조회 응답
@Builder(access = AccessLevel.PRIVATE)
public record ListUserResponse(
        List<FindUserResponse> responses
) {

    public static ListUserResponse from(ListUserQuery query) {
        return ListUserResponse.builder()
                .responses(query.queries().stream()
                        .map(FindUserResponse::from)
                        .toList())
                .build();
    }
}
