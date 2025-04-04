package lime1st.example.clean.user.adapter.in.web.dto.response;

import lime1st.example.clean.user.domain.exception.ErrorCode;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Builder
public record ErrorResponse(
        int status,
        Map<String, String> message
) {

    public static ResponseEntity<ErrorResponse> to(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.httpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.httpStatus().value())
                        .message(errorCode.message())
                        .build()
                );
    }
}
