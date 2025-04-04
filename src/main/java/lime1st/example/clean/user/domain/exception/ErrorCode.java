package lime1st.example.clean.user.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface ErrorCode {

    HttpStatus httpStatus();

    Map<String, String> message();
}
