package lime1st.example.clean.user.adapter.in.web.controller;

import lime1st.example.clean.user.adapter.in.web.dto.request.UpdateUserRequest;
import lime1st.example.clean.user.application.dto.command.UpdateUserCommand;
import lime1st.example.clean.user.application.port.in.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class PutUserController {

    private final UpdateUserUseCase updateUserUseCase;

    public PutUserController(UpdateUserUseCase updateUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putUser(
            @PathVariable String id,
            @RequestBody UpdateUserRequest request
    ) {
        UpdateUserCommand command = request.toWithId(UUID.fromString(id));
        updateUserUseCase.updateUser(command);
        return ResponseEntity.noContent().build();
    }
}
