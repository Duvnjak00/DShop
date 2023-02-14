package ba.sum.fsre.muzika.services;

import ba.sum.fsre.muzika.model.Music;
import ba.sum.fsre.muzika.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Music getMusicById(int id){
        return musicRepo.findById(id).get();
    }
    public void deleteMusicById(int id){
         musicRepo.deleteById(id);
    }
}
