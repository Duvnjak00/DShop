package ba.sum.fsre.muzika.controller;

import ba.sum.fsre.muzika.services.MyMusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyMusicListController {

    @Autowired
    private MyMusicListService service;

    @RequestMapping("/deleteMyList/{id}")
        public String deleteMyList(@PathVariable("id") int id){
            service.deleteById(id);
            return  "redirect:/my_music";
        }
}
