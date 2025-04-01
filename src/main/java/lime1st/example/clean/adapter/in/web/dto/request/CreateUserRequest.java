package lime1st.example.clean.adapter.in.web.dto.request;

import lime1st.example.clean.application.dto.command.CreateUserCommand;

// Create<Entity>Request 생성 요청
public record CreateUserRequest (
        String name,
        String email,
        String password
){
    public CreateUserCommand toCommand() {
        return new CreateUserCommand(
                null,
                name,
                email,
                password
        );
    }
}
