package ba.sum.fsre.dshop.repositories;

import ba.sum.fsre.dshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);

}
