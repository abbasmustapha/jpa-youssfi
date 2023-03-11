package com.mab.jpayoussfi.service;

import com.mab.jpayoussfi.entities.Role;
import com.mab.jpayoussfi.entities.User;
import com.mab.jpayoussfi.repositories.RoleRepository;
import com.mab.jpayoussfi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String roleName, String userName) {
        Role role = findRoleByRoleName(roleName);
        User user = findUserByUserName(userName);
        if(user.getRoles()!=null){

            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        // userRepository.save(user); pas utile car transaction commitée en fin de methode
    }

    @Override
    public User authenticate(String userName, String password) {
        User user=findUserByUserName(userName);
        if (user==null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Bad credentials");

    }
}
