package com.security.Security.Repo;

import com.security.Security.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyRepo extends JpaRepository<Users,Integer> {

    Users findByUserName(String username);
}
