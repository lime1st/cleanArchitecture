package lime1st.example.clean.adapter.out.persistence;

import lime1st.example.clean.application.dto.command.CreateUserCommand;
import lime1st.example.clean.application.dto.query.FindUserQuery;
import lime1st.example.clean.application.port.out.LoadUserPort;
import lime1st.example.clean.application.port.out.SaveUserPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort {

    private final UserRepository userRepository;
    private final UserMapper USERMAPPER = new UserMapper();

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID saveUser(CreateUserCommand command) {
        UserJpaEntity savedUser = userRepository.save(USERMAPPER.mapToJpaEntity(command));
        return savedUser.getId();
    }

    @Override
    public Optional<FindUserQuery> loadUser(UUID userId) {
        Optional<UserJpaEntity> findUser = userRepository.findById(userId);
        return findUser.map(USERMAPPER::mapToFindQuery);
    }
}

