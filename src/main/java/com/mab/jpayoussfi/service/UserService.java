package com.mab.jpayoussfi.service;

import com.mab.jpayoussfi.entities.Role;
import com.mab.jpayoussfi.entities.User;

public interface UserService {

    User addNewUser(User user);
    Role addNewRole(Role  role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String roleName, String userName);
    User authenticate(String userName, String password);
}
