package com.cbnits.authapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbnits.authapp.model.UserData;

/**
 * @author cbnits-154
 *
 */
public interface UserRepository extends JpaRepository<UserData, Integer> {

	public List<UserData> findByUsername(String username);

}
