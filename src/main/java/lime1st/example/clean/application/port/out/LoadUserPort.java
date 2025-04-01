package lime1st.example.clean.application.port.out;

import lime1st.example.clean.application.dto.query.FindUserQuery;

import java.util.Optional;
import java.util.UUID;

public interface LoadUserPort {

    Optional<FindUserQuery> loadUser(UUID userId);
}
