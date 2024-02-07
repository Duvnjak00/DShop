package ba.sum.fsre.dshop.services;

import ba.sum.fsre.dshop.model.User;
import ba.sum.fsre.dshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }
    @Transactional
    public void removeUserById(Integer id){
        userRepository.deleteById(id);
    }
    @Transactional
    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }
}
