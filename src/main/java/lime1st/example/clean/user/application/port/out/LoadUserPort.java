package lime1st.example.clean.user.application.port.out;

import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;

import java.util.Optional;
import java.util.UUID;

public interface LoadUserPort {

    Optional<FindUserQuery> loadUserById(UUID id);

    ListUserQuery loadUsers();
}
