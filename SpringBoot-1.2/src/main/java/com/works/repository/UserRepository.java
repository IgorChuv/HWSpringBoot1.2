package com.works.repository;

import com.works.model.Authorities;
import com.works.model.AuthorizedUser;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {

    private final List<AuthorizedUser> authorizedUserList = List.of(
            new AuthorizedUser("Igor", "1234"),
            new AuthorizedUser("Alex", "5678"),
            new AuthorizedUser("Egor", "91011"));

    public List<Authorities> getUserAuthorities(String user, String password) {

        List<Authorities> userAuthorities = new ArrayList<>();
        for (AuthorizedUser u : authorizedUserList) {
            if (u.getName().equals(user) & u.getPassword().equals(password)) {
                userAuthorities = List.of(Authorities.WRITE);
                if (user.equals("Igor") & password.equals("1234")) {
                    userAuthorities = List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
                }
            }
        }
        return userAuthorities;
    }
}