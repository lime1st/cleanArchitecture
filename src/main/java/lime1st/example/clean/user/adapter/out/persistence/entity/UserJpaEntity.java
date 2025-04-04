package lime1st.example.clean.user.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PACKAGE)
public class UserJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;
}
