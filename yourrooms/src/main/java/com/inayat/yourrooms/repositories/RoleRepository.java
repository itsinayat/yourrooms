package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
