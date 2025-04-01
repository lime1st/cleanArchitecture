package lime1st.example.clean.adapter.in.web;

import lime1st.example.clean.adapter.in.web.dto.request.CreateUserRequest;
import lime1st.example.clean.adapter.in.web.dto.response.FindUserResponse;
import lime1st.example.clean.application.dto.command.CreateUserCommand;
import lime1st.example.clean.application.dto.query.FindUserQuery;
import lime1st.example.clean.application.port.in.CreateUserUseCase;
import lime1st.example.clean.application.port.in.ReadUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

// 컨트롤러는 REST 요청을 유스케이스로 변환, 리포지토리는 JPA 로 구현.
@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ReadUserUseCase readUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, ReadUserUseCase readUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.readUserUseCase = readUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody CreateUserRequest request) {
        CreateUserCommand command = request.toCommand();
        UUID createdUserId = createUserUseCase.createUser(command);
        URI location = UriComponentsBuilder.newInstance()
                .path("/users/{userId}")
                .buildAndExpand(createdUserId)
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FindUserResponse> getUser(@PathVariable UUID userId) {
        FindUserQuery query = readUserUseCase.readUserById(userId);
        // 계층 dto 변환
        FindUserResponse response = FindUserResponse.from(query);
        return ResponseEntity.ok(response);
    }

    // Similar methods for Delete, Get and Update
}
