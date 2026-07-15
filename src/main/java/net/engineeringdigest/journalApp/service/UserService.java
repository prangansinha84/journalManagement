package net.engineeringdigest.journalApp.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save existing user (without encoding password)
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Create new normal user
    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));

            userRepository.save(user);

            return true;
        } catch (Exception e) {
            log.error("Error occurred for {} :", user.getUserName(), e);
            return false;
        }
    }

    // Create admin user
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));

        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Controller -> Service -> Repository -> Database

    public Optional<User> findById(@NonNull ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}