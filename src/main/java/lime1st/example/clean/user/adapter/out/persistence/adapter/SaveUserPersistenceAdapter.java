package lime1st.example.clean.user.adapter.out.persistence.adapter;

import lime1st.example.clean.user.adapter.out.persistence.entity.UserJpaEntity;
import lime1st.example.clean.user.adapter.out.persistence.entity.UserMapper;
import lime1st.example.clean.user.application.dto.command.CreateUserCommand;
import lime1st.example.clean.user.application.port.out.SaveUserPort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SaveUserPersistenceAdapter implements SaveUserPort {

    private final UserRepository repository;
    private final UserMapper userMapper = new UserMapper();

    public SaveUserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID saveUser(CreateUserCommand command) {
        UserJpaEntity savedUser = repository.save(userMapper.mapToJpaEntity(command));
        return savedUser.getId();
    }
}

