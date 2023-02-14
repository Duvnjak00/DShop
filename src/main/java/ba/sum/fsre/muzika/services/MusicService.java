package ba.sum.fsre.muzika.services;

import ba.sum.fsre.muzika.model.Music;
import ba.sum.fsre.muzika.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepo;
    public void save(Music m){
        musicRepo.save(m);
    }
    public List<Music> getAllMusic(){
        return musicRepo.findAll();
    }

}
