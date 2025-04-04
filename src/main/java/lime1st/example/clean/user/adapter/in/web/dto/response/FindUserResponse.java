package lime1st.example.clean.user.adapter.in.web.dto.response;

import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

// Find<Entity>Response 단일 조회 응답
@Builder(access = AccessLevel.PRIVATE)
public record FindUserResponse(
        UUID id,
        String name,
        String email,
        String password
) {

    public static FindUserResponse from(FindUserQuery query) {
        return FindUserResponse.builder()
                .id(query.id())
                .name(query.name())
                .email(query.email())
                .password(query.password())
                .build();
    }
}
