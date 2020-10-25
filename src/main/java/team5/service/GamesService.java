package team5.service;

import team5.entity.Games;
import team5.repositories.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GamesService {

    @Autowired
    GamesRepository gamesRepository;


    @Transactional
    public List<Games> getAllGames() {

        return gamesRepository.findAll();

    }

    @Transactional
    public Games getGamesByName(String name) {

        return gamesRepository.findByNameGame(name);

    }

}
