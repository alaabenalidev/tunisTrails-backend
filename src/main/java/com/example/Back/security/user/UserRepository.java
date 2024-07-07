package com.example.Back.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  boolean existsUserByEmail(String email);

  @Query("select u from User u where u.role = ?1")
  List<User> findAllByRole(Role role);

}
