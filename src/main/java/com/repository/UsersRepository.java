package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {

	List<User> findByUname(String uname);
	
}
