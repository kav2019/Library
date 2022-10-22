package library_project.service;

import library_project.models.User;
import library_project.repository.UserRepository;
import library_project.security.UserDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDet loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserDet(user.get());
    }
}
