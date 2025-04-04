package lime1st.example.clean.user.application.service;

import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;
import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.port.in.UpdateUserUseCase;
import lime1st.example.clean.user.application.port.out.LoadUserPort;
import lime1st.example.clean.user.application.port.out.UpdateUserPort;
import lime1st.example.clean.user.domain.User;
import lime1st.example.clean.user.domain.exception.CustomException;
import org.springframework.stereotype.Service;

import static lime1st.example.clean.user.domain.exception.UserErrorCode.USER_NOT_FOUND;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    public UpdateUserService(LoadUserPort loadUserPort, UpdateUserPort updateUserPort) {
        this.loadUserPort = loadUserPort;
        this.updateUserPort = updateUserPort;
    }

    @Override
    public void updateUser(UpdateUserCommand command) {
        FindUserQuery query = loadUserPort.loadUserById(command.id())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        // 업데이트 로직은 도메인에 있다.
        // JpaEntity 와 도메인이 분리되어 있으므로 JPA 의 Dirty Checking 은 작동하지 않는다.
        User user = query.to();
        User updateUser = user.update(command.newEmail(), command.newPassword());
        updateUserPort.updateUser(UpdateUserCommand.from(updateUser));
    }
}
