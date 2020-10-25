package team5.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Games {
    @Id
    @GeneratedValue
    private Long idGames;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CustomUser> users = new ArrayList<>();
    private String nameGame;
    private Integer wins;
    private Integer los;
    private Integer earning;

    public Games() {
    }

    public Long getIdGames() {
        return idGames;
    }

    public void setIdGames(Long idGames) {
        this.idGames = idGames;
    }

    public List<CustomUser> getUsers() {
        return users;
    }

    public void setUsers(List<CustomUser> users) {
        this.users = users;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLos() {
        return los;
    }

    public void setLos(Integer los) {
        this.los = los;
    }

    public Integer getEarning() {
        return earning;
    }

    public void setEarning(Integer earning) {
        this.earning = earning;
    }
}
