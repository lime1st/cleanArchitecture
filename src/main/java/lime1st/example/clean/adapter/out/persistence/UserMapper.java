package lime1st.example.clean.adapter.out.persistence;

import lime1st.example.clean.application.dto.command.CreateUserCommand;
import lime1st.example.clean.application.dto.query.FindUserQuery;

public class UserMapper {

    public UserJpaEntity mapToJpaEntity(CreateUserCommand command) {
        return new UserJpaEntity(
                command.name(),
                command.email(),
                command.password()
        );
    }

    public CreateUserCommand mapToCreateCommand(UserJpaEntity jpaEntity) {
        return new CreateUserCommand(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword()
        );
    }

    public FindUserQuery mapToFindQuery(UserJpaEntity jpaEntity) {
        return new FindUserQuery(
                jpaEntity.getName(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword()
        );
    }
}
