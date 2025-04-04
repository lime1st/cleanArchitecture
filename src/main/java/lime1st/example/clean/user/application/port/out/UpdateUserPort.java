package lime1st.example.clean.user.application.port.out;

import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;

public interface UpdateUserPort {

    void updateUser(UpdateUserCommand command);
}
