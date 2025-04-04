package lime1st.example.clean.user.application.service;

import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;
import lime1st.example.clean.user.application.port.in.ReadUserUseCase;
import lime1st.example.clean.user.application.port.out.LoadUserPort;
import lime1st.example.clean.user.domain.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lime1st.example.clean.user.domain.exception.UserErrorCode.USER_NOT_FOUND;

@Service
public class ReadUserService implements ReadUserUseCase {

    private final LoadUserPort loadUserPort;

    public ReadUserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public FindUserQuery readUserById(UUID id) {
        return loadUserPort.loadUserById(id)
                .orElseThrow(()-> new CustomException(USER_NOT_FOUND));
    }

    @Override
    public ListUserQuery readUsers() {
        return loadUserPort.loadUsers();
    }
}
