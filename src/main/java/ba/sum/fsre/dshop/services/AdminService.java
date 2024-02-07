package ba.sum.fsre.dshop.services;
import ba.sum.fsre.dshop.model.Role;
import ba.sum.fsre.dshop.model.User;
import ba.sum.fsre.dshop.repositories.RoleRepository;
import ba.sum.fsre.dshop.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createAdmin() {
        String adminEmail = "admin@gmail.com";
        if (userRepository.findUserByEmail(adminEmail).isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");

            User newUser = new User();
            newUser.setEmail(adminEmail);
            newUser.setFirstname("Admin");
            newUser.setLastname("Admin");
            newUser.setPassword(passwordEncoder.encode("admin"));

            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            newUser.setRoles(roles);

            userRepository.save(newUser);
        }
    }
}