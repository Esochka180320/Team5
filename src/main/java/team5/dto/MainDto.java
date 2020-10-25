package team5.dto;

import team5.entity.CustomUser;
import team5.entity.Games;

import java.util.ArrayList;
import java.util.List;

public class MainDto {
    private String userName;
    private List<String> gamesName = new ArrayList<>();
    private Integer balans;

    public MainDto of(CustomUser user, List<Games> games){
        this.userName=user.getName();
        this.balans=user.getBalans();
        for (Games g: games) {
            this.gamesName.add(g.getNameGame());
        }
    return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getGamesName() {
        return gamesName;
    }

    public void setGamesName(List<String> gamesName) {
        this.gamesName = gamesName;
    }

    public Integer getBalans() {
        return balans;
    }

    public void setBalans(Integer balans) {
        this.balans = balans;
    }
}
