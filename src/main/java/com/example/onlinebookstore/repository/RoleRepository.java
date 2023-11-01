package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Role;
import com.example.onlinebookstore.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(RoleName name);
}
