package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByUname(String uname);
	List<Order> findByOrderid(int orderid);
}
