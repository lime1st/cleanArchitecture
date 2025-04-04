package lime1st.example.clean.user.adapter.in.web.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class FindUserResponseJsonTest {

    @Autowired
    private JacksonTester<FindUserResponse> json;

    @Test
    @DisplayName("응답 객체 직렬화 테스트")
    void serialization() throws IOException {
        // given
        var user = new FindUserResponse(
                UUID.randomUUID(),
                "alice",
                "alice@mail.com",
                "password"
        );

        // when
        var jsonContent = json.write(user);

        // then, UUID, LocalDateTime 등의 객체는 비교 시 toString()으로 변환해 주어야 한다.
        assertThat(jsonContent).extractingJsonPathStringValue("@.id")
                .isEqualTo(user.id().toString());
        assertThat(jsonContent).extractingJsonPathStringValue("@.name")
                .isEqualTo(user.name());
        assertThat(jsonContent).extractingJsonPathStringValue("@.email")
                .isEqualTo(user.email());
        assertThat(jsonContent).extractingJsonPathStringValue("@.password")
                .isEqualTo(user.password());
    }
}