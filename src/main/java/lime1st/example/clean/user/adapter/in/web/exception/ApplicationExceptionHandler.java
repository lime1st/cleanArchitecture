package lime1st.example.clean.user.adapter.in.web.exception;

import lime1st.example.clean.user.adapter.in.web.dto.response.ErrorResponse;
import lime1st.example.clean.user.domain.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * Handle CustomException
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handle(CustomException e) {
        return ErrorResponse.to(e.getErrorCode());
    }

    /**
     * Handle MethodArgumentNotValidException
     * (RequestBody Validation)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = e.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> (error instanceof FieldError) ? ((FieldError) error).getField() : error.getObjectName(),
                        error -> Optional.ofNullable(error.getDefaultMessage()).orElse("No error message"),
                        (existing, replacement) -> existing
                ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(errorMap)
                        .build()
                );
    }
}
