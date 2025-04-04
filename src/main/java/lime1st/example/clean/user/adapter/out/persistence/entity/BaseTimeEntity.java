package lime1st.example.clean.user.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Auditing Data Class
 * @EntityListeners(AuditingEntityListener.class) 스프링 부트 3.2 부터 생략이 가능하다.
 * AuditorAware 를 사용할 경우 명시적으로 추가해야 사용자 정보 추적이 가능하다.
 */
@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    private LocalDateTime deletedAt;

    private String deletedBy;
}