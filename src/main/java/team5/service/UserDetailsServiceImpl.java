package team5.service;

import team5.entity.CustomUser;
import team5.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        CustomUser customUser = userService.findByEmail(email);




        String emal = customUser.getEmail();
        String password = customUser.getPassword();


        if (customUser.getEmail()==null)
            throw new UsernameNotFoundException(email + " not found");

        List<GrantedAuthority> roles =
                Arrays.asList(
                        new SimpleGrantedAuthority(Role.USER.toString()));

        return new User(customUser.getEmail(),
                customUser.getPassword(),roles);
    }
}
