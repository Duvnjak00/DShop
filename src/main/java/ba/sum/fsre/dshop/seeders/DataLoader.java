package ba.sum.fsre.dshop.seeders;

import ba.sum.fsre.dshop.model.Role;
import ba.sum.fsre.dshop.repositories.RoleRepository;
import ba.sum.fsre.dshop.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final AdminService adminService;

    @Autowired
    public DataLoader(RoleRepository roleRepository, AdminService adminService) {
        this.roleRepository = roleRepository;
        this.adminService = adminService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
    }

    private void loadRoles() {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
        adminService.createAdmin();
    }

    private Role createRoleIfNotExists(String roleName) {
        Role existingRole = roleRepository.findByName(roleName);
        if (existingRole != null) {
            return existingRole;
        } else {
            Role newRole = new Role();
            newRole.setName(roleName);
            return roleRepository.save(newRole);
        }
    }
}