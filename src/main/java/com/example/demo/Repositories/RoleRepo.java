package com.example.demo.Repositories;

import com.example.demo.Model.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<AppRole, Long> {
    AppRole findAppRoleByRoleName(String roleName);
}
