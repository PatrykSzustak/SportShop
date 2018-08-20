package solshop.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solshop.user.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findOneByEmail(String email);


}
