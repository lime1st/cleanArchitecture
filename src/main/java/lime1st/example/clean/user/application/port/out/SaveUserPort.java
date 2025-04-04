package lime1st.example.clean.user.application.port.out;

import lime1st.example.clean.user.application.dto.command.CreateUserCommand;

import java.util.UUID;

public interface SaveUserPort {

    UUID saveUser(CreateUserCommand command);
}
