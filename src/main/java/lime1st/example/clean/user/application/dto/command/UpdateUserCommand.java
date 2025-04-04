package lime1st.example.clean.user.application.dto.command;

import lime1st.example.clean.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

// Update<Entity>Command 기존 엔티티 수정
@Builder(access = AccessLevel.PRIVATE)
public record UpdateUserCommand(
        UUID id,
        String name,
        String newEmail,
        String newPassword
) {

    public static UpdateUserCommand of(
            UUID id,
            String newEmail,
            String newPassword
    ) {
        return UpdateUserCommand.builder()
                .id(id)
                .newEmail(newEmail)
                .newPassword(newPassword)
                .build();
    }

    public static UpdateUserCommand from(User user) {
        return UpdateUserCommand.builder()
                .id(user.id())
                .name(user.name())
                .newEmail(user.email())
                .newPassword(user.password())
                .build();
    }
}
