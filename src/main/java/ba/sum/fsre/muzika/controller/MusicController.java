package ba.sum.fsre.muzika.controller;

import ba.sum.fsre.muzika.model.Music;
import ba.sum.fsre.muzika.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MusicController {
    @Autowired
    private MusicService service;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/music")
    public ModelAndView getAllMusic() {
        List<Music> list = service.getAllMusic();
        return new ModelAndView("musicList", "music", list);
    }
    @PostMapping("/save")
    public String addMusic(@ModelAttribute Music m){
        service.save(m);
        return "redirect:/music";
    }


}

