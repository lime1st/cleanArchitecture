package lime1st.example.clean.application.service;

import lime1st.example.clean.application.dto.command.CreateUserCommand;
import lime1st.example.clean.application.dto.query.FindUserQuery;
import lime1st.example.clean.application.port.in.CreateUserUseCase;
import lime1st.example.clean.application.port.in.ReadUserUseCase;
import lime1st.example.clean.application.port.out.LoadUserPort;
import lime1st.example.clean.application.port.out.SaveUserPort;
import lime1st.example.clean.domain.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements CreateUserUseCase, ReadUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;

    public UserService(SaveUserPort saveUserPort, LoadUserPort loadUserPort) {
        this.saveUserPort = saveUserPort;
        this.loadUserPort = loadUserPort;
    }

    @Override
    public UUID createUser(CreateUserCommand command) {
        if (!validateEmail(command.email())) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (!validatePassword(command.password())) {
            throw new IllegalArgumentException("Invalid password");
        }

        User user = User.create(command.name(), command.email(), command.password());
        // 도메인 생성 시 비즈니스 로직 호출
        return saveUserPort.saveUser(CreateUserCommand.fromDomain(user));
    }

    @Override
    public FindUserQuery readUserById(UUID userId) {
        return loadUserPort.loadUser(userId)
                .orElseThrow(()-> new RuntimeException("User with id " + userId + " not found"));
    }

    private boolean validateEmail(String email) {
        return email.contains("@");
    }

    private boolean validatePassword(String password) {
        return password.length() > 4;
    }
}
