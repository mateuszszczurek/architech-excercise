package com.architech.exercise.accounts.registration;

import com.architech.exercise.accounts.users.User;
import com.architech.exercise.accounts.users.UsernameAlreadyExistsException;
import com.architech.exercise.accounts.users.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService  {

    @Autowired
    private UsersDao usersDao;

    public void register(RegisterAccountRequest registerAccountRequest) throws UsernameAlreadyExistsException {

        String username = registerAccountRequest.getUsername();
        Optional<User> alreadyExistingUser = usersDao.findUser(username);

        if(alreadyExistingUser.isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user =  new User(registerAccountRequest.getUsername(), registerAccountRequest.getPassword());
        usersDao.storeUser(user);

    }
}
