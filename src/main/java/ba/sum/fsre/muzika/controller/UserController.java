package ba.sum.fsre.muzika.controller;

import ba.sum.fsre.muzika.model.User;
import ba.sum.fsre.muzika.model.UserDetails;
import ba.sum.fsre.muzika.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public String listUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("activeLink", "Korisnici");
        return "users";
    }
}