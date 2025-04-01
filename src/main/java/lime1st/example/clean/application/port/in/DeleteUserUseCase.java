package lime1st.example.clean.application.port.in;

import lime1st.example.clean.application.dto.command.DeleteUserCommand;

public interface DeleteUserUseCase {

    void deleteUser(DeleteUserCommand command);
}
