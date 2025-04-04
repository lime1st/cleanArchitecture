package lime1st.example.clean.user.adapter.in.web.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ListUserResponseJsonTest {

    @Autowired
    private JacksonTester<ListUserResponse> json;

    @Test
    @DisplayName("응답 목록 객체 직렬화 테스트")
    void serialize() throws IOException {
        // given
        var users = new ListUserResponse(
                List.of(
                        new FindUserResponse(
                                UUID.randomUUID(),
                                "alice",
                                "alice@mail.com",
                                "password"
                        ),
                        new FindUserResponse(
                                UUID.randomUUID(),
                                "bob",
                                "bob@mail.com",
                                "password"
                        )
                )
        );

        // when
        var jsonContent = json.write(users);

        // then
        assertThat(jsonContent).isNotNull();

        // 객체 안의 List 검증
        assertThat(jsonContent).extractingJsonPathArrayValue("$.responses")
                .hasSize(2);
        assertThat(jsonContent).extractingJsonPathValue("$.responses[0].id")
                .isEqualTo(users.responses().get(0).id().toString());
        assertThat(jsonContent).extractingJsonPathValue("$.responses[0].name")
                .isEqualTo(users.responses().get(0).name());
        assertThat(jsonContent).extractingJsonPathValue("$.responses[0].email")
                .isEqualTo(users.responses().get(0).email());
        assertThat(jsonContent).extractingJsonPathValue("$.responses[0].password")
                .isEqualTo(users.responses().get(0).password());
    }
}