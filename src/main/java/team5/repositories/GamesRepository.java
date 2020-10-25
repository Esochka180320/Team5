package team5.repositories;

import team5.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games,Long> {


    List<Games> findAll();

    Games findByNameGame(String nameGame);
}
