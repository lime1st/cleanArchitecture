package lime1st.example.clean.user.adapter.out.persistence.adapter;

import lime1st.example.clean.user.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserJpaEntity, UUID> {
}
