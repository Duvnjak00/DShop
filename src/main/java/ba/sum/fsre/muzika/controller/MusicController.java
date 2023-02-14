package ba.sum.fsre.muzika.controller;

import ba.sum.fsre.muzika.model.Music;
import ba.sum.fsre.muzika.model.MyMusicList;
import ba.sum.fsre.muzika.services.MusicService;
import ba.sum.fsre.muzika.services.MyMusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MusicController {
    @Autowired
    private MusicService service;

    @Autowired
    private MyMusicListService myMusicService;
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
    @GetMapping("/my_music")
    public String getMyMusic(Model model){
        List<MyMusicList>list=myMusicService.getAllMyMusic();
        model.addAttribute("music", list);
        return "my_music";
    }

    @RequestMapping("/mylist/{id}")
        public String getMyList(@PathVariable("id")int id){
            Music m=service.getMusicById(id);
            MyMusicList mm= new MyMusicList(m.getId(), m.getName(), m.getAuthor(), m.getLink());
            myMusicService.saveMyMusic(mm);
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

