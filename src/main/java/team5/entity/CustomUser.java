package team5.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomUser {


    @Id
    @GeneratedValue
    private Long idUser;
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
    private Integer balans;
    private Integer age;
    private String avatar;
    private String hobby;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @OneToMany(mappedBy = "users")
    private List<GamesTransactions> gamesTransactions;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Games> games = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Role role;

    public List<GamesTransactions> getGamesTransactions() {
        return gamesTransactions;
    }

    public void setGamesTransactions(List<GamesTransactions> gamesTransactions) {
        this.gamesTransactions = gamesTransactions;
    }

    public CustomUser() {
    }


    public CustomUser(String name, String email, String password, Integer balans, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balans = balans;
        this.role = role;
    }

    public CustomUser(String name, String email, String password, Integer balans) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balans = balans;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalans() {
        return balans;
    }

    public void setBalans(Integer balans) {
        this.balans = balans;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public List<Games> getGames() {
        return games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }
}
