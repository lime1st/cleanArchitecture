package lime1st.example.clean.application.dto.command;

import lime1st.example.clean.domain.User;

import java.util.UUID;

// Create<Entity>Command 새 엔티티 생성
public record CreateUserCommand(
        UUID id,
        String name,
        String email,
        String password
) {

    public static CreateUserCommand fromDomain(User user) {
        return new CreateUserCommand(
                user.id(),
                user.name(),
                user.email(),
                user.password()
        );
    }
}
