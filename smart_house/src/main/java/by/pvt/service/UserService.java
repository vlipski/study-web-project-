package by.pvt.service;


import by.pvt.pojo.User;
import by.pvt.pojo.cmd.UserSignUpCmd;
import by.pvt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public String creatUser(UserSignUpCmd userSignUpCmd) {
        if (userRepository.getUserByLogin(userSignUpCmd.getLogin()) != null) {
            return "a user with that username already exists";
        }
        userRepository.save(new User(userSignUpCmd.getName(),
                userSignUpCmd.getLogin(),
                passwordEncoder.encode(userSignUpCmd.getPassword())));
        return null;

    }


}
