package com.example.beardculture.repository;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleNameEnum role);
}
