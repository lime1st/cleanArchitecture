package lime1st.example.clean.domain;

import java.util.UUID;

// 순수 POJO, Hibernate 나  JPA 의존성 없음.
public record User(
        UUID id,
        String name,
        String email,
        String password
) {
    public static User create(String name, String email, String password) {
        // password encoding 은 어디서?
        return new User(
                UUID.randomUUID(),
                name,
                email,
                password
        );
    }

    public User updateWithEmail(String newEmail) {
        return new User(id, name, newEmail, password);
    }
}
