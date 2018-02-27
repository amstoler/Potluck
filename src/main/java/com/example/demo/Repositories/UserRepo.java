package com.example.demo.Repositories;

import com.example.demo.Model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AppUser,Long> {
    AppUser findAppUserByUsername(String username);
    AppUser findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}
