package lime1st.example.clean.user.adapter.out.persistence.adapter;

import lime1st.example.clean.user.adapter.out.persistence.entity.UserJpaEntity;
import lime1st.example.clean.user.adapter.out.persistence.entity.UserMapper;
import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;
import lime1st.example.clean.user.application.port.out.LoadUserPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoadUserPersistenceAdapter implements LoadUserPort {

    private final UserRepository repository;
    private final UserMapper userMapper = new UserMapper();

    public LoadUserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<FindUserQuery> loadUserById(UUID id) {
        Optional<UserJpaEntity> findUser = repository.findById(id);
        return findUser.map(userMapper::mapToFindQuery);
    }

    @Override
    public ListUserQuery loadUsers() {
        List<UserJpaEntity> findUsers = repository.findAll();
        List<FindUserQuery> findUserQueries = findUsers.stream()
                .map(userMapper::mapToFindQuery)
                .toList();
        return new ListUserQuery(findUserQueries);
    }
}
