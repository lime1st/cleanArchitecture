package lime1st.example.clean.user.adapter.in.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lime1st.example.clean.user.adapter.in.web.dto.request.CreateUserRequest;
import lime1st.example.clean.user.adapter.in.web.dto.response.FindUserResponse;
import lime1st.example.clean.user.application.dto.command.CreateUserCommand;
import lime1st.example.clean.user.application.service.CreateUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PostUserController.class)
class PostUserControllerWebMvcTest {

    @Autowired
    private MockMvcTester mvcTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateUserService createUserService;

    @Test
    @DisplayName("User 생성 요청 성공 테스트")
    void create() throws JsonProcessingException {
        // given
        UUID userId = UUID.randomUUID();
        var request = new CreateUserRequest(
                "alice",
                "alice@mail.com",
                "password"
        );

        var userRequest = objectMapper.writeValueAsString(request);

        given(createUserService.createUser(any(CreateUserCommand.class)))
                .willReturn(userId);

        // when & then
        assertThat(mvcTester.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRequest)
        ).hasStatus(HttpStatus.CREATED)
                .hasHeader("Location", "/api/users/" + userId);
    }
}