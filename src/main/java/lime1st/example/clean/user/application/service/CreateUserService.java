package lime1st.example.clean.user.application.service;

import lime1st.example.clean.user.application.dto.command.CreateUserCommand;
import lime1st.example.clean.user.application.port.in.CreateUserUseCase;
import lime1st.example.clean.user.application.port.out.SaveUserPort;
import lime1st.example.clean.user.domain.User;
import lime1st.example.clean.user.domain.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lime1st.example.clean.user.domain.exception.UserErrorCode.INVALID_PASSWORD;
import static lime1st.example.clean.user.domain.exception.UserErrorCode.USER_CONFLICT;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final SaveUserPort saveUserPort;

    public CreateUserService(SaveUserPort saveUserPort) {
        this.saveUserPort = saveUserPort;
    }

    @Override
    public UUID createUser(CreateUserCommand command) {
        if (!validateEmail(command.email())) {
            throw new CustomException(USER_CONFLICT);
        }

        if (!validatePassword(command.password())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        User user = User.create(command.name(), command.email(), command.password());
        // 도메인 생성 시 비즈니스 로직 호출
        return saveUserPort.saveUser(CreateUserCommand.from(user));
    }

    private boolean validateEmail(String email) {
        // 중복 검사를 해야 한다.
        return email.contains("@");
    }

    private boolean validatePassword(String password) {
        // password 설정 규칙을 검사해야 한다.
        return password.length() > 4;
    }
}
