package lime1st.example.clean.application.port.in;

import lime1st.example.clean.application.dto.query.FindUserQuery;

import java.util.UUID;

public interface ReadUserUseCase {

    // 예외 처리를 application 레이어에서 하기 위해 Optional 을 넣지 않았다.
    FindUserQuery readUserById(UUID userId);

//    FindUserQuery readUserByEmail(String email);
}
