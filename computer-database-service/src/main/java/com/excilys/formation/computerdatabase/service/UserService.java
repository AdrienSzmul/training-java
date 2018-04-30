package com.excilys.formation.computerdatabase.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.excilys.formation.computerdatabase.model.Users;
import com.excilys.formation.computerdatabase.persistence.dao.UserDAO;

public class UserService implements UserDetailsService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users user = userDAO.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "UserName " + username + " not found");
        } else {
            return new User(user.getUsername(), user.getPassword(), null);
        }
    }
}
