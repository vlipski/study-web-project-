package by.pvt.service;


import by.pvt.pojo.User;
import by.pvt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                roles
        );
        return userSpring;
    }


}
