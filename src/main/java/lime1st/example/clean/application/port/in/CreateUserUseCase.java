package lime1st.example.clean.application.port.in;


import lime1st.example.clean.application.dto.command.CreateUserCommand;

import java.util.UUID;

// 비즈니스 로직만 포함, 기술 세부사항(예: Hibernate) 몰라야 함.
public interface CreateUserUseCase {

    UUID createUser(CreateUserCommand command);
}

// Similar classes for DeleteUser, GetUser, and UpdateUser

