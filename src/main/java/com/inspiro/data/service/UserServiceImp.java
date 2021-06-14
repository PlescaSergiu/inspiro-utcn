package com.inspiro.data.service;

import com.inspiro.data.dao.UserRepository;
import com.inspiro.data.entity.Post;
import com.inspiro.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByPost(Post post){
        return userRepository.findByPostsIsContaining(post);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return user;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getByActivationCode(String activationCode) {
        return userRepository.getByActivationCode(activationCode);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
