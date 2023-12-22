package EmployeesOf.G.G.services;

import EmployeesOf.G.G.dto.UserInfoDetails;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private  UsersRepository userRepository;
    @Autowired
    @Lazy
    private  PasswordEncoder passwordEncoder;

    public UserInfoService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userInfo = userRepository.findByUserName(username);

        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    public String addUsers(Users user) {
        Users exist = userRepository.findByUserName(user.getUserName()).orElse(null);
        if(exist==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "New User Added SuccessFully!";
        }else {
            throw new UsernameNotFoundException(user.getUserName());
        }
    }

    public Users getUser(int id){
        return userRepository.findById(id).orElse(null);
    }

}
