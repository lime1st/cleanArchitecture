package lime1st.example.clean.user.adapter.in.web.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CreateUserRequestJsonTest {

    @Autowired
    private JacksonTester<CreateUserRequest> json;

    @Test
    @DisplayName("User 생성 요청 데이터 역직렬화 테스트")
    void deserialize() throws IOException {
        // given
        var content = """
                {
                    "name": "alice",
                    "email": "alice@mail.com",
                    "password": "password"
                }
                """;

        // when & then
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new CreateUserRequest(
                        "alice",
                        "alice@mail.com",
                        "password"
                ));
    }
}