package lime1st.example.clean.user.adapter.in.web.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateUserRequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("유효한 값으로 User 수정 요청")
    void validUserRequest() {
        // given
        var user = new UpdateUserRequest(
                "alice@mail.com",
                "password"
        );

        // when
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(user);

        // then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("유효하지 않은 값으로 User 수정 요청")
    void invalidUserRequest() {
        // given
        var user = new UpdateUserRequest(
                "alice@mail.com",
                ""
        );

        // when
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(user);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("비밀번호를 입력하세요.");
    }
}