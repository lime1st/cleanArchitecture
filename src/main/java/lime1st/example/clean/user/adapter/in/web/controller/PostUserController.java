package lime1st.example.clean.user.adapter.in.web.controller;

import lime1st.example.clean.user.adapter.in.web.dto.request.CreateUserRequest;
import lime1st.example.clean.user.application.dto.command.CreateUserCommand;
import lime1st.example.clean.user.application.port.in.CreateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

// 컨트롤러는 REST 요청을 유스케이스로 변환, 리포지토리는 JPA 로 구현.
@RestController
@RequestMapping("/api/users")
public class PostUserController {

    private final CreateUserUseCase createUserUseCase;

    public PostUserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody CreateUserRequest request) {
        CreateUserCommand command = request.to();
        UUID createdUserId = createUserUseCase.createUser(command);
        URI location = UriComponentsBuilder.newInstance()
                .path("/api/users/{id}")
                .buildAndExpand(createdUserId)
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }
}
