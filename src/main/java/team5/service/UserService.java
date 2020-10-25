package team5.service;

import team5.entity.CustomUser;
import team5.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {




    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public CustomUser findByEmail(String email) {

        return customUserRepository.findByEmail(email);

    }

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return customUserRepository.findAll();
    }


    @Transactional
    public boolean saveUser(CustomUser customUser) {

        if (customUserRepository.existsByEmail(customUser.getEmail()) || customUserRepository.existsByName(customUser.getName())) {
            return false;
        }

        customUserRepository.save(customUser);
        return true;
    }


    @Transactional
    public void updateUser(String login, String email, String phone) {
        CustomUser user = customUserRepository.findByEmail(email);
        if (user == null)
            return;

        user.setEmail(email);


        customUserRepository.save(user);
    }

    @Transactional
    public boolean sendUrlToChangePassword(String email) {

        CustomUser user = customUserRepository.findByEmail(email);

        if (user == null) {
            return false;
        } else {

            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to iShop. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getUuid()

            );

            mailSender.send(user.getEmail(), "Change password", message);

            return true;
        }


    }


    @Transactional
    public boolean changePassword(CustomUser user, String password) {

        if (user == null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(password));
        customUserRepository.save(user);


        return true;
    }


    @Transactional
    public CustomUser findByUuid(String uuid) {
        CustomUser user =  customUserRepository.findByUuid(uuid);
        return user;
    }


    @Transactional
    public CustomUser findByName(String name) {

        return customUserRepository.findByName(name);

    }


    @Transactional
    public void updateInformation(String hobby, Integer age, String avatar, String userName) {

        CustomUser customUser = customUserRepository.findByName(userName);
        if (!hobby.isEmpty()) {
            customUser.setHobby(hobby);
        }
        if (age != null) {
            customUser.setAge(age);
        }
        if (!avatar.isEmpty()) {
            customUser.setAvatar(avatar);
        }
    }


    @Transactional
    public void updateInformation2(String password, Integer age, String hobby) {

        CustomUser customUser = customUserRepository.findByName(getNameCurrentUser());
        if (!hobby.isEmpty()) {
            customUser.setHobby(hobby);
        }
        if (age != null) {
            customUser.setAge(age);
        }
        if (!password.isEmpty()) {
            customUser.setPassword(password);
        }
    }

    private String getNameCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String name = loggedInUser.getName();


        return name;
    }

    @Transactional
    public void updateBalance(CustomUser customUser, Integer amount) {
        customUser.setBalans(amount);
    }
}
