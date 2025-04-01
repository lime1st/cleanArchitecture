package lime1st.example.clean.application.port.out;

import lime1st.example.clean.application.dto.command.CreateUserCommand;

import java.util.UUID;

public interface SaveUserPort {

    UUID saveUser(CreateUserCommand command);
}
