package ba.sum.fsre.muzika.repositories;

import ba.sum.fsre.muzika.model.MyMusicList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMusicRepository extends JpaRepository<MyMusicList, Integer>
    {

}
