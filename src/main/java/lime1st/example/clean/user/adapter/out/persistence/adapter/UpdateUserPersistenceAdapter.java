package lime1st.example.clean.user.adapter.out.persistence.adapter;

import lime1st.example.clean.user.adapter.out.persistence.entity.UserMapper;
import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;
import lime1st.example.clean.user.application.port.out.UpdateUserPort;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateUserPersistenceAdapter implements UpdateUserPort {

    private final UserRepository repository;
    private final UserMapper userMapper = new UserMapper();

    public UpdateUserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateUser(UpdateUserCommand command) {
        repository.save(userMapper.mapToJpaEntity(command));
    }
}
