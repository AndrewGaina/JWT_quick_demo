package com.exampleJWT.JWTdemo.Service;

import com.exampleJWT.JWTdemo.Model.Role;
import com.exampleJWT.JWTdemo.Model.UserApp;
import com.exampleJWT.JWTdemo.Repo.RoleRepo;
import com.exampleJWT.JWTdemo.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserApp saveUser(UserApp userApp) {
        log.info("Adding userApp {}", userApp);
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return userRepo.save(userApp);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Adding role {}", role);
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to {}", roleName, username);
        UserApp userApp = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        userApp.getRoles().add(role);
    }

    @Override
    public UserApp getUser(String name) {
        log.info("Getting user with name {}", name);
        return userRepo.findByUsername(name);
    }

    @Override
    public List<UserApp> getUsers() {
        log.info("Getting users with name");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepo.findByUsername(username);
        if (userApp == null) throw new UsernameNotFoundException("User not found");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userApp.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(userApp.getUsername(), userApp.getPassword(), authorities);
    }
}
