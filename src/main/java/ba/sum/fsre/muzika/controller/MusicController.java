package ba.sum.fsre.muzika.controller;

import ba.sum.fsre.muzika.model.Music;
import ba.sum.fsre.muzika.model.User;
import ba.sum.fsre.muzika.model.UserDetails;
import ba.sum.fsre.muzika.repositories.UserRepository;
import ba.sum.fsre.muzika.services.MusicService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class MusicController {
    @Autowired
    private MusicService service;

    @Autowired
    private UserRepository userRepo;

    @PersistenceContext
    EntityManager entityManager;


    @GetMapping("/music")
    public ModelAndView getAllMusic() {
        List<Music> list = service.getAllMusic();
        return new ModelAndView("musicList", "music", list);
    }
    @GetMapping("/new_music")
    public String newMusic(){
        return "new_music";
    }

    @PostMapping("/save")
    public String addMusic(@ModelAttribute Music m){
        service.save(m);
        return "redirect:/music";
    }
    @GetMapping("/my_music")
    public String getMyMusic(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        model.addAttribute("music", u.getMyMusic());
        return "my_music";
    }

    @RequestMapping("/deleteMyList/{id}")
    @Transactional
    public String deleteMyList(@PathVariable("id") int id){
        Music m=service.getMusicById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        u.removeMyMusic(m);
        entityManager.persist(u);
        return  "redirect:/my_music";
    }

    @RequestMapping("/mylist/{id}")
    @Transactional
    public String getMyList(@PathVariable("id")int id){
        Music m=service.getMusicById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User u = userRepo.findByEmail(userDetails.getUser().getEmail());
        u.addMyMusic(m);
        entityManager.persist(u);
        return "redirect:/music";
    }
    @RequestMapping("/editMusic/{id}")
    public String editMusic(@PathVariable("id") int id, Model model){
       Music m=service.getMusicById(id);
       model.addAttribute("music", m);
       return "music_edit";
    }
    @RequestMapping("/deleteMusic/{id}")
    public String deleteMusic(@PathVariable("id") int id){
        service.deleteMusicById(id);
        return "redirect:/music";
    }
}

