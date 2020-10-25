package team5.service;

import org.springframework.stereotype.Service;


public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
