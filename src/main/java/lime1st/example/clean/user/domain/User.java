package lime1st.example.clean.user.domain;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

// 순수 POJO, Hibernate 나  JPA 의존성 없음.
@Builder(access = AccessLevel.PRIVATE)
public record User(
        UUID id,
        String name,
        String email,
        String password,
        String deletedBy,
        LocalDateTime deletedAt
) {
    public static User create(String name, String email, String password) {
        // password encoding 은 어디서?
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public User update(String newEmail, String newPassword) {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(newEmail)
                .password(newPassword)
                .build();
    }

    public User withEmail(String newEmail) {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(newEmail)
                .password(this.password)
                .build();
    }

    public User updateWithPassword(String newPassword) {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(newPassword)
                .build();
    }

    public User deleteOf(String deletedBy) {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .deletedBy(deletedBy)
                .deletedAt(LocalDateTime.now())
                .build();
    }
}