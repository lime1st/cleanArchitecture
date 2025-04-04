package lime1st.example.clean.user.application.dto.command;

import lime1st.example.clean.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

// Create<Entity>Command 새 엔티티 생성
@Builder(access = AccessLevel.PRIVATE)
public record CreateUserCommand(
        String name,
        String email,
        String password
) {

    public static CreateUserCommand of(
            String name,
            String email,
            String password
    ) {
        return CreateUserCommand.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public static CreateUserCommand from(User user) {
        return CreateUserCommand.builder()
                .name(user.name())
                .email(user.email())
                .password(user.password())
                .build();
    }
}
