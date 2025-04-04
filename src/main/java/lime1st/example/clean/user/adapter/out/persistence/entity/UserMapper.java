package lime1st.example.clean.user.adapter.out.persistence.entity;

import lime1st.example.clean.user.application.dto.command.CreateUserCommand;
import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;
import lime1st.example.clean.user.application.dto.query.FindUserQuery;

public class UserMapper {

    public UserJpaEntity mapToJpaEntity(CreateUserCommand command) {
        return UserJpaEntity.builder()
                .name(command.name())
                .email(command.email())
                .password(command.password())
                .build();
    }

    public UserJpaEntity mapToJpaEntity(UpdateUserCommand command) {
        return UserJpaEntity.builder()
                .id(command.id())
                .name(command.name())
                .email(command.newEmail())
                .password(command.newPassword())
                .build();
    }

    public FindUserQuery mapToFindQuery(UserJpaEntity jpaEntity) {
        return FindUserQuery.builder()
                .id(jpaEntity.getId())
                .name(jpaEntity.getName())
                .email(jpaEntity.getEmail())
                .password(jpaEntity.getPassword())
                .build();
    }
}
