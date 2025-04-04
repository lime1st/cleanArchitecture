package lime1st.example.clean.user.application.port.out;

import java.util.UUID;

public interface DeleteUserPort {

    void deleteUserById(UUID id);
}
