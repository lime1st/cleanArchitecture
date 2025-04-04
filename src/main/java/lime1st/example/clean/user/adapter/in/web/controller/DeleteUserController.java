package lime1st.example.clean.user.adapter.in.web.controller;

import lime1st.example.clean.user.application.port.out.DeleteUserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class DeleteUserController {

    private final DeleteUserPort deleteUserPort;

    public DeleteUserController(DeleteUserPort deleteUserPort) {
        this.deleteUserPort = deleteUserPort;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID id
    ) {
        deleteUserPort.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
