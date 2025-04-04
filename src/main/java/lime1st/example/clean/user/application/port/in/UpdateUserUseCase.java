package lime1st.example.clean.user.application.port.in;

import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;

public interface UpdateUserUseCase {

    void updateUser(UpdateUserCommand command);
}
