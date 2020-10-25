package team5.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class GamesTransactions {
    @Id
    @GeneratedValue
    private Long idGamesTransactions;
    @ManyToOne
    private CustomUser users;
    private Integer money;
    private Timestamp time;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GamesTransactions(CustomUser users, Integer money, Timestamp time) {
        this.users = users;

        this.money = money;
        this.time = time;
    }

    public GamesTransactions() {
    }

    public Long getIdGamesTransactions() {
        return idGamesTransactions;
    }

    public void setIdGamesTransactions(Long idGamesTransactions) {
        this.idGamesTransactions = idGamesTransactions;
    }

    public CustomUser getUsers() {
        return users;
    }

    public void setUsers(CustomUser users) {
        this.users = users;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
