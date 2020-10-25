package team5.controller;

import team5.dto.RatingDto;
import team5.entity.CustomUser;
import team5.entity.Games;
import team5.repositories.CustomUserRepository;
import team5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.service.GamesService;
import team5.service.SecurityService;
import team5.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GamesService gamesService;


    @RequestMapping("/")
    String showSign_inPage() {
        return "sign_in";
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "sign_in";
    }


    @RequestMapping("/change_password")
    String changePassw() {
        return "change_password";
    }

    @RequestMapping("/errorPage")
    String errorUrl() {
        return "error";
    }


    @RequestMapping("/logout")
    String logout() {
        return "login";
    }

    @RequestMapping("/sign_in?error=user-exist")
    String userExist() {
        return "sign_in";
    }


    @RequestMapping("/registration1")
    ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("sign_up");
        return modelAndView;
    }


    @RequestMapping("/account")
    ModelAndView account() {
        ModelAndView modelAndView = new ModelAndView("Account");
        return modelAndView;
    }

    @RequestMapping("/registration")
    ModelAndView registration(@RequestParam("fullname") String fullname,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password) {


        ModelAndView modelAndView = new ModelAndView("sign_in");

        String uuid = UUID.randomUUID().toString();
        String[] pib = new String[3];

        String passHash = passwordEncoder.encode(password);

        CustomUser customUser = new CustomUser(fullname, email, passHash, 200);
        customUser.setUuid(uuid);
        if (!userService.saveUser(customUser)) {
            modelAndView.addObject("message", "Error!! User with typed email exist!");
            return modelAndView;
        } else {
            securityService.autoLogin(email, password);
           // modelAndView.addObject("message", "Sign in to continue");
            return new ModelAndView("sign_in");
        }
    }


    @RequestMapping("/send_mail")
    ModelAndView getPassword(@RequestParam String email) {

        ModelAndView modelAndView = new ModelAndView("sign_in");
        String message = "";

        if (!userService.sendUrlToChangePassword(email)) {
            message = "User with this email not exist!";

        } else message = "Check your email to change password!!";

        modelAndView.addObject("message", message);

        return modelAndView;
    }




    @RequestMapping("/activate/{code}")
    public String activate(@PathVariable("code") String code, HttpServletRequest request) {

        CustomUser customUser = userService.findByUuid(code);

        if (customUser != null) {
            request.getSession().setAttribute("user", customUser);
            return "redirect:/change_password";
        } else return "errorPage";
    }


    @RequestMapping("/change")
    public ModelAndView changePassword(@RequestParam("password") String password,
                                       HttpServletRequest request
    ) {

        ModelAndView modelAndView = new ModelAndView("sign_in");
        ModelAndView fail = new ModelAndView("errorPage");
        if (userService.changePassword((CustomUser) request.getSession().getAttribute("user"), password)) {
            modelAndView.addObject("message", "Your password was changed");
            return modelAndView;
        } else return fail;


    }

    private String getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();
        return  userService.findByEmail(email).getName();


    }



    @RequestMapping("/main")
    public ModelAndView mainPage() {

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }



    @RequestMapping("/getGames")
    public ModelAndView getGames() {

        ModelAndView modelAndView = new ModelAndView("rating");
        modelAndView.addObject(gamesService.getAllGames());

       return modelAndView;
    }

    @RequestMapping("/deposite")
    public ModelAndView deposit() {

        ModelAndView modelAndView = new ModelAndView("Deposit");

        return modelAndView;
    }

    @RequestMapping("/get-raiting")
    public ModelAndView getGames(@RequestParam("name") String name) {

        ModelAndView modelAndView = new ModelAndView("rating");

        Games games= gamesService.getGamesByName(name);
        RatingDto ratingDto = new RatingDto();

        modelAndView.addObject(ratingDto.of(games));

        return modelAndView;
    }







}
