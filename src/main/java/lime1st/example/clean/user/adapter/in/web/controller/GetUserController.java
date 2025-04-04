package lime1st.example.clean.user.adapter.in.web.controller;

import lime1st.example.clean.user.adapter.in.web.dto.response.FindUserResponse;
import lime1st.example.clean.user.adapter.in.web.dto.response.ListUserResponse;
import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;
import lime1st.example.clean.user.application.port.in.ReadUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class GetUserController {

    private final ReadUserUseCase readUserUseCase;

    public GetUserController(ReadUserUseCase readUserUseCase) {
        this.readUserUseCase = readUserUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindUserResponse> getUser(@PathVariable UUID id) {
        FindUserQuery query = readUserUseCase.readUserById(id);
        // 계층 dto 변환
        FindUserResponse response = FindUserResponse.from(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ListUserResponse> getUsers() {
        ListUserQuery query = readUserUseCase.readUsers();

        // 계층 dto 변환
        ListUserResponse responses = ListUserResponse.from(query);
        return ResponseEntity.ok(responses);
    }
}
