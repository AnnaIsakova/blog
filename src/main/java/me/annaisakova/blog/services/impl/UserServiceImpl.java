package me.annaisakova.blog.services.impl;


import me.annaisakova.blog.entities.User;
import me.annaisakova.blog.repositories.UserRepository;
import me.annaisakova.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
