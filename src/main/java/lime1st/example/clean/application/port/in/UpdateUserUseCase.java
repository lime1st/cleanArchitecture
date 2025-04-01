package lime1st.example.clean.application.port.in;

import lime1st.example.clean.application.dto.command.UpdateUserCommand;

public interface UpdateUserUseCase {

    void updateUser(UpdateUserCommand command);
}
