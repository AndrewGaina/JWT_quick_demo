package com.exampleJWT.JWTdemo.Service;

import com.exampleJWT.JWTdemo.Model.Role;
import com.exampleJWT.JWTdemo.Model.UserApp;

import java.util.List;

public interface UserService {
    UserApp saveUser(UserApp userApp);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    UserApp getUser(String name);
    List<UserApp> getUsers();
}
