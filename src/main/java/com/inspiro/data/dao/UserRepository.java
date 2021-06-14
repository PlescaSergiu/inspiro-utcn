package com.inspiro.data.dao;


import com.inspiro.data.entity.Post;
import com.inspiro.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
    User getByActivationCode(String activationCode);

    User findByPostsIsContaining(Post post);
    User findByUsername(String username);

}
