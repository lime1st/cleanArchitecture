package lime1st.example.clean.user.application.port.in;

import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;

import java.util.UUID;

public interface ReadUserUseCase {

    // 예외 처리를 application 레이어에서 하기 위해 Optional 을 넣지 않았다.
    FindUserQuery readUserById(UUID id);

//    FindUserQuery readUserByEmail(String email);

    ListUserQuery readUsers();
}
