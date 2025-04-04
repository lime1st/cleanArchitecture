package lime1st.example.clean.user.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, Map.of("404", "User Not Found")),
    USER_CONFLICT(HttpStatus.CONFLICT, Map.of("409", "User Conflict")),

    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, Map.of("400", "Invalid Password")),

    ;

    private final HttpStatus httpStatus;
    private final Map<String, String> message;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public Map<String, String> message() {
        return message;
    }
}
