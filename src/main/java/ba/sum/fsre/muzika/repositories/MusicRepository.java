package ba.sum.fsre.muzika.repositories;

import ba.sum.fsre.muzika.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {

}
