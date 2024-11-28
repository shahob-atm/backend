package org.example.backend.repository;

import org.example.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findAllByName(String name);
}
