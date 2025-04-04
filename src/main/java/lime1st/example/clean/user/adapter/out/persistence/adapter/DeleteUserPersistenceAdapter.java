package lime1st.example.clean.user.adapter.out.persistence.adapter;

import lime1st.example.clean.user.application.port.out.DeleteUserPort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DeleteUserPersistenceAdapter implements DeleteUserPort {

    private final UserRepository repository;

    public DeleteUserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUserById(UUID id) {
        repository.deleteById(id);
    }
}
