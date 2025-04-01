package lime1st.example.clean.adapter.in.web.dto.response;

import lime1st.example.clean.application.dto.query.FindUserQuery;

// Find<Entity>Response 단일 조회 응답
public record FindUserResponse(
        String name,
        String email,
        String password
) {

    public static FindUserResponse from(FindUserQuery query) {
        return new FindUserResponse(
                query.name(),
                query.email(),
                query.password()
        );
    }
}
