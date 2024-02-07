package ba.sum.fsre.dshop.controller;

import ba.sum.fsre.dshop.global.GlobalData;
import ba.sum.fsre.dshop.model.Role;
import ba.sum.fsre.dshop.model.User;
import ba.sum.fsre.dshop.repositories.RoleRepository;
import ba.sum.fsre.dshop.repositories.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(Model model){
        GlobalData.cart.clear();
        model.addAttribute("user", new User());
        return "login";
    }
    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        System.out.println("User firstname: " + user.getFirstname());

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);

        userRepository.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/login";
    }
    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "forgotPassword";
    }


}
