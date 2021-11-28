package com.example.beardculture.repository;

import com.example.beardculture.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllBy();
}
