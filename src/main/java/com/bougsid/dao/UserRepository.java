package com.bougsid.dao;

import com.bougsid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ayoub on 17/12/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);
}
