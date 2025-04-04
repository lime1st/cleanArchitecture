package lime1st.example.clean.user.application.service;

import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.port.in.DeleteUserUseCase;
import lime1st.example.clean.user.application.port.out.DeleteUserPort;
import lime1st.example.clean.user.application.port.out.LoadUserPort;
import lime1st.example.clean.user.domain.User;
import lime1st.example.clean.user.domain.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lime1st.example.clean.user.domain.exception.UserErrorCode.USER_NOT_FOUND;

@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    private final LoadUserPort loadUserPort;

    public DeleteUserService(DeleteUserPort deleteUserPort, LoadUserPort loadUserPort) {
        this.deleteUserPort = deleteUserPort;
        this.loadUserPort = loadUserPort;
    }

    @Override
    public void deleteUserById(UUID id) {
        FindUserQuery query = loadUserPort.loadUserById(id)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        User user = query.to();
        user.deleteOf("who");
        // TODO: 실제 삭제가 아니라 소프트 딜리트 정책 적용하도록 수정.
        deleteUserPort.deleteUserById(id);
    }
}
