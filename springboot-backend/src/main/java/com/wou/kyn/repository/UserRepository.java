package com.wou.kyn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wou.kyn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmailOrUsername(String email, String username);

	List<User> findByUserIdIn(List<Long> userIds);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
