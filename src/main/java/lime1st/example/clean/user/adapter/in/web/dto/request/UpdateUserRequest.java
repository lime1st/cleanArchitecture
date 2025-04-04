package lime1st.example.clean.user.adapter.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;

import java.util.UUID;

// Update<Entity>Request 수정 요청
public record UpdateUserRequest(
        @NotBlank(message = "이메일을 입력하세요.")
            @Email(message = "이메일 형식을 지켜주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력하세요.")
        String password
) {

    public UpdateUserCommand toWithId(UUID id) {
        return UpdateUserCommand.of(
                id,
                email,
                password
        );
    }
}
