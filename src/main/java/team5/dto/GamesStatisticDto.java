package team5.dto;

import team5.entity.CustomUser;
import team5.entity.Games;
import team5.entity.GamesTransactions;
import team5.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GamesStatisticDto {
    private List<String> name = new ArrayList<>();
    private List<Integer> los = new ArrayList<>();
    private List<Integer> wins = new ArrayList<>();
    private List<Integer> earning = new ArrayList<>();
    private List<Integer> money = new ArrayList<>();
    private List<Timestamp> time = new ArrayList<>();

@Autowired
GamesService gamesService;
    public GamesStatisticDto of(CustomUser user) {

        List<GamesTransactions> gamesTransactions = user.getGamesTransactions();

        for (GamesTransactions g : gamesTransactions) {
            money.add(g.getMoney());
            name.add(g.getGameName());
            Games games =gamesService.getGamesByName(g.getGameName());
            los.add(games.getLos());
            wins.add(games.getWins());
            earning.add(games.getEarning());
            time.add(g.getTime());

        }
        return this;
    }
}
