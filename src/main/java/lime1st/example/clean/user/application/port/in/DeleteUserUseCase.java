package lime1st.example.clean.user.application.port.in;

import java.util.UUID;

public interface DeleteUserUseCase {

    void deleteUserById(UUID id);
}
