package lime1st.example.clean.user.adapter.in.web.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UpdateUserRequestJsonTest {

    @Autowired
    private JacksonTester<UpdateUserRequest> json;

    @Test
    @DisplayName("User 수정 객체 역직렬화 테스트")
    void deserialize() throws IOException {
        // given
        var content = """
                {
                    "email": "newAlice@mail.com",
                    "password": "newPassword"
                }
                """;

        // when & then
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new UpdateUserRequest(
                        "newAlice@mail.com",
                        "newPassword"
                ));
    }
}