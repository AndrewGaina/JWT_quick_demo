package com.exampleJWT.JWTdemo.Repo;

import com.exampleJWT.JWTdemo.Model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);
}
