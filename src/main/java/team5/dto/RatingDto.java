package team5.dto;

import team5.entity.CustomUser;
import team5.entity.Games;

import java.util.ArrayList;
import java.util.List;

public class RatingDto {

    private List<String> userName = new ArrayList<>();
    private List<Integer> wins = new ArrayList<>();
    private List<Integer> loses = new ArrayList<>();
    private List<Integer> coef = new ArrayList<>();

    public RatingDto of(Games games) {

        List<CustomUser> users = games.getUsers();


        for (CustomUser c : users
        ) {
            userName.add(c.getName());
            Games gamesForThisUser = c.getGames().stream().filter(g -> g.getNameGame().equals(games.getNameGame())).findFirst().get();
            this.wins.add(gamesForThisUser.getWins());
            this.loses.add(gamesForThisUser.getLos());
            this.coef.add(gamesForThisUser.getWins() / gamesForThisUser.getLos());

        }
return this;
    }


}
