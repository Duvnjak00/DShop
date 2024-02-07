package ba.sum.fsre.dshop.services;

import ba.sum.fsre.dshop.model.Role;
import ba.sum.fsre.dshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRoleById(Integer id){
        return roleRepository.findById(id);
    }
}
