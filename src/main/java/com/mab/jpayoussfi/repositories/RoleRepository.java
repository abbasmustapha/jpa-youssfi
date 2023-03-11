package com.mab.jpayoussfi.repositories;

import com.mab.jpayoussfi.entities.Role;
import com.mab.jpayoussfi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
