package com.cg.ima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ima.entities.ERole;
import com.cg.ima.entities.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole name);

}
