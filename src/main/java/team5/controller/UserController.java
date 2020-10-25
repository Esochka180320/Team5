package team5.controller;


import team5.dto.MainDto;
import team5.entity.*;
import team5.entity.CustomUser;
import team5.entity.Games;
import team5.service.GamesService;
import team5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    GamesService gamesService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "is-user-auth", method = RequestMethod.POST)
    public String isUserAuth() {

        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());
        if (customUser == null) {
            return "no";
        } else return "yes";

    }

    @RequestMapping("/update-inf")
    public ModelAndView updateInformation(@RequestParam("hobby") String hobby,
                                          @RequestParam("age") Integer age,
                                          @RequestParam("avatar") String avatar,
                                          @RequestParam("userName") String userName) {

        ModelAndView modelAndView = new ModelAndView("user");

        userService.updateInformation(hobby, age, avatar, userName);
        return modelAndView;

    }


    @RequestMapping("/chenge")
    boolean chenge(@RequestParam String password,
                   @RequestParam String age,
                   @RequestParam String hobby) {



        userService.updateInformation2( password, Integer.parseInt(age), hobby);
        return true;
    }


    private String getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();


        return email;
    }

    @RequestMapping("/main-page")
    public MainDto mainPage2() {


        CustomUser customUser=userService.findByName(getEmailCurrentUser2());;

        List<Games> games = gamesService.getAllGames();

        MainDto mainDto = new MainDto();
        return mainDto.of(customUser,games);


    }


    private String getEmailCurrentUser2() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();
        return  userService.findByEmail(email).getName();


    }


    @RequestMapping("/get-deposite/{amount}")
    public boolean getDeposite(@PathVariable("amount") Integer amount) {

        CustomUser customUser= userService.findByName(getEmailCurrentUser2());

        userService.updateBalance(customUser,amount);

        return true;
    }


}
