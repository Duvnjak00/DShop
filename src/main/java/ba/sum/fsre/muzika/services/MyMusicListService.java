package ba.sum.fsre.muzika.services;

import ba.sum.fsre.muzika.model.MyMusicList;
import ba.sum.fsre.muzika.repositories.MyMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMusicListService {

    @Autowired
    private MyMusicRepository mymusic;
    public void saveMyMusic(MyMusicList music){
        mymusic.save(music);
    }
    public List<MyMusicList> getAllMyMusic(){
        return mymusic.findAll();
    }
    public void deleteById(int id){
        mymusic.deleteById(id);
    }
}