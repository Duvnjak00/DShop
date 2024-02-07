package ba.sum.fsre.dshop.repositories;

import ba.sum.fsre.dshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    Role findByName(String role_admin);
}
